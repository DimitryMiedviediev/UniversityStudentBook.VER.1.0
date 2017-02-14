package model.filters;

import java.io.*;
import javax.servlet.*;

/**
 * Created by Dimitry on 14.02.17.
 */
public class FormEncodingSetterFilter implements Filter {

    // кодировка
    private String encoding;

    public void init(FilterConfig config) throws ServletException {
        // читаем из конфигурации
        encoding = config.getInitParameter("requestEncoding");

        // если не установлена - устанавливаем Cp1251
        if (encoding == null)
            encoding = "utf-8";
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain next)
            throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        next.doFilter(request, response);
    }

    public void destroy() {
    }

//    private FilterConfig filterConfig;
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        this.filterConfig = filterConfig;
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
//        String charEncod = filterConfig.getInitParameter("characterEncoding");
//        String charEncodRequest = req.getCharacterEncoding();
//        if (charEncodRequest == null && charEncod != null) {
//            try {
//                req.setCharacterEncoding(charEncod);
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
////                servletRequest.setAttribute("error", e);
////                RequestDispatcher rd = req.getRequestDispatcher("control_panel");
////                rd.forward(req, resp);
//            }
//        }
//        chain.doFilter(req, resp);
//    }
    /////////////////////

//    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
//        String charEncod = filterConfig.getInitParameter("characterEncoding");
//        String charEncodRequest = req.getCharacterEncoding();
//        if (charEncodRequest == null && charEncod != null) {
//            try {
//                req.setCharacter
// Encoding(charEncod);
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
////       Добавить когда будет страница с ошибками
////                servletRequest.setAttribute("error", e);
////                RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher("errorpage");
////                requestDispatcher.forward(servletRequest,servletResponse);
//            }
//        }
//        chain.doFilter(req, resp);
//    }
//    private static final String FILTERABLE_CONTENT_TYPE="application/x-www-form-urlencoded";
//
//    private static final String ENCODING_DEFAULT = "UTF-8";
//
//    private static final String ENCODING_INIT_PARAM_NAME = "encoding";
//
//    private String encoding;
//
//    public void destroy(){
//    }
//
//    public void doFilter(ServletRequest req, ServletResponse resp,
//                         FilterChain chain) throws ServletException, IOException{
//        String contentType = req.getContentType();
//        if (contentType != null && contentType.startsWith(FILTERABLE_CONTENT_TYPE))
//            req.setCharacterEncoding(encoding);
//        chain.doFilter(req, resp);
//    }
//
//    public void init(FilterConfig config) throws ServletException{
//        encoding = config.getInitParameter(ENCODING_INIT_PARAM_NAME);
//        if (encoding == null)
//            encoding = ENCODING_DEFAULT;
//    }

}

