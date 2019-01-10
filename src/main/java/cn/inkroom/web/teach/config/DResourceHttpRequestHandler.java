package cn.inkroom.web.teach.config;

import cn.inkroom.web.teach.entities.DDRRC;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.HttpRange;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.ResourceRegionHttpMessageConverter;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.accept.PathExtensionContentNegotiationStrategy;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.resource.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/9/20
 * @Time 16:25
 * @Descorption
 */
public class DResourceHttpRequestHandler extends ResourceHttpRequestHandler {
    public DResourceHttpRequestHandler(String parent,ResourceHttpRequestHandler handler) {
        this.setResourceTransformers(new ArrayList<ResourceTransformer>());
        this.setResourceHttpMessageConverter(new ResourceHttpMessageConverter());



        ArrayList<Resource> resources = new ArrayList<Resource>();
        try {
            resources.add(new UrlResource("file:" + parent));

            this.setLocations(resources);

            ArrayList<ResourceResolver> resolvers = new ArrayList<ResourceResolver>();
            resolvers.add(new PathResourceResolver());

            this.setResourceResolvers(resolvers);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Resource resource = this.getResource(request);
        MediaType mediaType = new MediaType("octet-stream");
        if (mediaType != null) {
            ResourceRegionHttpMessageConverter converter = new ResourceRegionHttpMessageConverter();
            {
                ServletServerHttpResponse outputMessage = new ServletServerHttpResponse(response);
                if (request.getHeader("Range") == null) {
                    this.setHeaders(response, resource, mediaType);
                    converter.write(resource, mediaType, outputMessage);
                } else {
                    response.setHeader("Accept-Ranges", "bytes");
                    ServletServerHttpRequest inputMessage = new ServletServerHttpRequest(request);

                    try {
                        List<HttpRange> httpRanges = inputMessage.getHeaders().getRange();
                        response.setStatus(206);
                        if (httpRanges.size() == 1) {
                            ResourceRegion resourceRegion = ((HttpRange) httpRanges.get(0)).toResourceRegion(resource);
                            converter.write(resourceRegion, mediaType, outputMessage);
                        } else {
                            converter.write(HttpRange.toResourceRegions(httpRanges, resource), mediaType, outputMessage);
                        }
                    } catch (IllegalArgumentException var9) {
                        response.setHeader("Content-Range", "bytes */" + resource.contentLength());
                        response.sendError(416);
                    }
                }
            }
        }
    }

    @Override
    public Resource getResource(HttpServletRequest request) throws IOException {
        String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        System.out.println("locations = " + super.getLocations() + "   res=" + super.getResourceResolvers());
        System.out.println("找到的路径 = " + path + "  " + super.processPath(path) + "    检验的结果==" + super.isInvalidPath(path));
        Resource r = super.getResource(request);
        System.out.println(" 找到的资源 = " + r);

        ResourceResolverChain resolveChain = new DDRRC(this.getResourceResolvers());
        Resource resource = resolveChain.resolveResource(request, path, this.getLocations());
//        if(resource != null && !this.getResourceTransformers().isEmpty()) {
//            ResourceTransformerChain transformChain = new DefaultResourceTransformerChain(resolveChain, this.getResourceTransformers());
//            resource = transformChain.transform(request, resource);
//            return resource;
//        } else {
        return resource;
//        }


    }

}
