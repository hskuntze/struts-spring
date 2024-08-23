package br.com.kuntze.strutspring.controllers;

import com.opensymphony.xwork2.ActionSupport;

public class RedirectAction extends ActionSupport {
    private static final long serialVersionUID = 1L;

    @Override
    public String execute() {
        return SUCCESS; // Redireciona para /login.jsp conforme configurado no struts.xml
    }
}
