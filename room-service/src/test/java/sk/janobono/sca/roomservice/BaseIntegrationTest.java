package sk.janobono.sca.roomservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
@Testcontainers
public abstract class BaseIntegrationTest {

    @Container
    public static MySQLContainer<?> mysqlDB = new MySQLContainer<>
            ("mysql:8-debian")
            .withDatabaseName("room_service")
            .withUsername("app")
            .withPassword("app")
            .withEnv("MYSQL_ROOT_HOST", "%");

    @DynamicPropertySource
    public static void properties(DynamicPropertyRegistry registry) throws Exception {
        registry.add("spring.datasource.url",
                () -> "jdbc:mysql://localhost:" + mysqlDB.getMappedPort(MySQLContainer.MYSQL_PORT) + "/room_service"
        );
    }

    @Value("${local.server.port}")
    public int serverPort;

    @Autowired
    public Flyway flyway;

    @Autowired
    public MockMvc mvc;

    @Autowired
    public WebApplicationContext webApplicationContext;

    @Autowired
    public ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        flyway.clean();
        flyway.migrate();
        mvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    public String mapToJson(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }

    public <T> T mapFromJson(String json, Class<T> clazz) throws IOException {
        return objectMapper.readValue(json, clazz);
    }

    public <T> Page<T> mapPagedResponse(String json, Class<T> paramClazz)
            throws IOException {
        JsonNode parent = objectMapper.readTree(json);
        return new PageImpl<>(
                getListFromNode(parent.get("content"), paramClazz),
                PageRequest.of(
                        parent.get("pageable").get("pageNumber").asInt(),
                        parent.get("pageable").get("pageSize").asInt()),
                parent.get("totalElements").asLong());
    }

    public <T> List<T> getListFromNode(JsonNode node, Class<T> clazz) throws IOException {
        List<T> content = new ArrayList<>();
        for (JsonNode val : node) {
            content.add(objectMapper.readValue(val.traverse(), clazz));
        }
        return content;
    }
}
