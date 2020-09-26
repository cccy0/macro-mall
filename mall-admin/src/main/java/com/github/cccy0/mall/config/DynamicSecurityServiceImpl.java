package com.github.cccy0.mall.config;

import com.github.cccy0.mall.model.UmsResource;
import com.github.cccy0.mall.security.component.DynamicSecurityService;
import com.github.cccy0.mall.service.UmsResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Zhai
 * 2020/9/25 21:37
 */
@Service
public class DynamicSecurityServiceImpl implements DynamicSecurityService {
    private UmsResourceService umsResourceService;

    @Autowired
    public void setUmsResourceService(UmsResourceService umsResourceService) {
        this.umsResourceService = umsResourceService;
    }

    @Override
    public Map<String, ConfigAttribute> loadDataSource() {
        ConcurrentHashMap<String, ConfigAttribute> map = new ConcurrentHashMap<>();
        List<UmsResource> resources = umsResourceService.listAll();
        for (UmsResource resource : resources) {
            map.put(resource.getUrl(), new SecurityConfig(resource.getId() + ":" + resource.getName()));
        }
        return map;
    }
}
