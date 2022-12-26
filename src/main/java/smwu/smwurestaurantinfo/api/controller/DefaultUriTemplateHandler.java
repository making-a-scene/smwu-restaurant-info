package smwu.smwurestaurantinfo.api.controller;

import org.springframework.web.util.UriTemplateHandler;

import java.net.URI;
import java.util.Map;

public class DefaultUriTemplateHandler implements UriTemplateHandler {
    @Override
    public URI expand(String uriTemplate, Map<String, ?> uriVariables) {
        return null;
    }

    @Override
    public URI expand(String uriTemplate, Object... uriVariables) {
        return null;
    }
}
