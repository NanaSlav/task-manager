package ru.nanaslav.projectsmicroservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name ="users-microservice")
public interface UsersProxy {

    @GetMapping("/users/current-username")
    public ResponseEntity<String> getCurrentUsername(@RequestHeader("Authorization") String token);
}
