package cn.inkroom.web.teach.entities;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/9/20
 * @Time 22:14
 * @Descorption
 */
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import org.springframework.core.io.Resource;
import org.springframework.util.Assert;
import org.springframework.web.servlet.resource.ResourceResolver;
import org.springframework.web.servlet.resource.ResourceResolverChain;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;//package org.springframework.web.servlet.resource;


public class DDRRC implements ResourceResolverChain {
    private final List<ResourceResolver> resolvers = new ArrayList();
    private int index = -1;

    public DDRRC(List<? extends ResourceResolver> resolvers) {
        if(resolvers != null) {
            this.resolvers.addAll(resolvers);
        }

    }

    public Resource resolveResource(HttpServletRequest request, String requestPath, List<? extends Resource> locations) {
        ResourceResolver resolver = this.getNext();
        if(resolver == null) {
            return null;
        } else {
            Resource var5;
            try {
                var5 = resolver.resolveResource(request, requestPath, locations, this);
            } finally {
                --this.index;
            }

            return var5;
        }
    }

    public String resolveUrlPath(String resourcePath, List<? extends Resource> locations) {
        ResourceResolver resolver = this.getNext();
        if(resolver == null) {
            return null;
        } else {
            String var4;
            try {
                var4 = resolver.resolveUrlPath(resourcePath, locations, this);
            } finally {
                --this.index;
            }

            return var4;
        }
    }

    private ResourceResolver getNext() {
        Assert.state(this.index <= this.resolvers.size(), "Current index exceeds the number of configured ResourceResolvers");
        if(this.index == this.resolvers.size() - 1) {
            return null;
        } else {
            ++this.index;
            return this.resolvers.get(this.index);
        }
    }
}

