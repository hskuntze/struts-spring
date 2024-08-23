package br.com.kuntze.strutspring.controllers;

import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class ProtectedResourceController extends ActionSupport {
	private static final long serialVersionUID = 7780344320307592461L;

	public String execute() throws Exception {
        HttpSession session = ServletActionContext.getRequest().getSession();
        String accessToken = (String) session.getAttribute("accessToken");

        if (accessToken == null) {
            return LOGIN; // Redireciona para login se não estiver autenticado
        }

        // Configurar a requisição para acessar um recurso protegido
        HttpGet get = new HttpGet("http://localhost:8080/api/protected/resource");
        get.setHeader("Authorization", "Bearer " + accessToken);

        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse response = client.execute(get);

        if (response.getStatusLine().getStatusCode() == 200) {
            // Processar a resposta do recurso protegido
            String resourceResponse = EntityUtils.toString(response.getEntity());
            // Processar a resposta conforme necessário
            return SUCCESS;
        } else {
            // Tratar falhas de autorização
            addActionError("Unauthorized!");
            return ERROR;
        }
    }
}
