package congvanservice.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@ConfigurationProperties(prefix = "security-auth")
@Component
class ResourceServerClientConfig {
    Boolean enabled;
}
