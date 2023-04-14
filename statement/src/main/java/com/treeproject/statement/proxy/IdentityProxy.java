package com.treeproject.statement.proxy;

import com.treeproject.statement.model.dto.IsAllowedPermissionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "IDENTITY", path = "/identity")
public interface IdentityProxy {
    @RequestMapping(value = "/user/allowed", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    ResponseEntity<?> isAllowed(@RequestBody  IsAllowedPermissionDto dto, @RequestHeader("Authorization") String token);
}
