package br.com.kuntze.strutspring.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.util.EntityUtils;
import org.apache.struts2.ServletActionContext;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensymphony.xwork2.ActionSupport;

public class OAuthController extends ActionSupport {
	private static final long serialVersionUID = 8652928274294265255L;
	
	private String username;
    private String password;

    public String execute() throws Exception {
        // URL do endpoint OAuth2 do seu servidor
        String oauthTokenUrl = "http://localhost:8080/oauth/token";
        
        // Configurar a requisição POST para obter o token
        HttpPost post = new HttpPost(oauthTokenUrl);
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("grant_type", "password"));
        params.add(new BasicNameValuePair("username", username));
        params.add(new BasicNameValuePair("password", password));
        params.add(new BasicNameValuePair("client_id", "your-client-id"));
        params.add(new BasicNameValuePair("client_secret", "your-client-secret"));
        post.setEntity(new UrlEncodedFormEntity(params));
        
        // Enviar a requisição e capturar a resposta
        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse response = client.execute(post);
        
        if (response.getStatusLine().getStatusCode() == 200) {
            // Parse do token de acesso da resposta JSON
            String jsonResponse = EntityUtils.toString(response.getEntity());
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(jsonResponse);
            String accessToken = node.get("access_token").asText();
            
            // Armazenar o token em sessão
            HttpSession session = ServletActionContext.getRequest().getSession();
            session.setAttribute("accessToken", accessToken);

            return SUCCESS;
        } else {
            // Tratar falhas de autenticação
            addActionError("Login failed!");
            return ERROR;
        }
    }

    // Getters e Setters para username e password
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
