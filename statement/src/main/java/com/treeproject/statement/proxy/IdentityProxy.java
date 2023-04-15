package com.treeproject.statement.proxy;

import com.treeproject.statement.model.dto.IsAllowedPermissionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "IDENTITY", path = "/identity")
public interface IdentityProxy {
    @PostMapping(value = "/user/allowed",consumes = "application/json", produces = "application/json")
    ResponseEntity<Void> isAllowed(@RequestBody  IsAllowedPermissionDto dto, @RequestHeader("Authorization") String token);
}
