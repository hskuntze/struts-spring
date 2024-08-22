package br.com.kuntze.strutspring.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.action.ServletRequestAware;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.rest.HttpHeaders;

import com.opensymphony.xwork2.ActionSupport;

@Namespace("/oauth")
public class OAuthController extends ActionSupport implements ServletRequestAware {
	private static final long serialVersionUID = 6296493874441071416L;
	private HttpServletRequest request;

	//POST
	@Action("token")
	public HttpHeaders create() {
		 StringBuilder body = new StringBuilder();
        try {
            BufferedReader reader = request.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                body.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Agora, a variável `body` contém o conteúdo do corpo da requisição
        System.out.println("Body: " + body.toString());
        
        return null; // Substitua conforme necessário
	}

	@Override
	public void withServletRequest(HttpServletRequest request) {
		this.request = request;
	}
}
