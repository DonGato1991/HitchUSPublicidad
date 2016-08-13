/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.rest.client;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import ec.edu.espe.models.EstadisticaCampania;

/**
 * Jersey REST client generated for REST resource:EstadisticaCampaniaFacadeREST
 * [ec.edu.espe.entities.estadisticacampania]<br>
 * USAGE:
 * <pre>
 *        EstadisticaCampaniaRestClient client = new EstadisticaCampaniaRestClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author homer
 */
public class EstadisticaCampaniaRestClient {

    private WebTarget webTarget;
    private Client client;
    //private static final String BASE_URI = "http://localhost:8080/PublicidadREST/webresources";
    private static final String BASE_URI = "http://52.34.202.157:8080/PublicidadWebHitchUs/webresources";

    public EstadisticaCampaniaRestClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("estadisticacampania");
    }

    public String countREST() throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("count");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public void edit_XML(EstadisticaCampania requestEntity, String id) throws ClientErrorException {
        webTarget
                .path("edit/"+id)
                .request(javax.ws.rs.core.MediaType.APPLICATION_XML)
                .put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
    }

    public void edit_JSON(EstadisticaCampania requestEntity, String id) throws ClientErrorException {
        webTarget
                .path("edit/"+id)
                .request(javax.ws.rs.core.MediaType.APPLICATION_JSON)
                .put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public <T> T find_XML(Class<T> responseType, String id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("find/{0}", new Object[]{id}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public <EstadisticaCampania> EstadisticaCampania find_JSON(Class<EstadisticaCampania> responseType, String id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("find/"+id);
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T findRange_XML(Class<T> responseType, String from, String to) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}/{1}", new Object[]{from, to}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T findRange_JSON(Class<T> responseType, String from, String to) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}/{1}", new Object[]{from, to}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void create_XML(Object requestEntity) throws ClientErrorException {
        webTarget.path("create").request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
    }

    public void create_JSON(EstadisticaCampania requestEntity) throws ClientErrorException {
        webTarget.path("create")
                .request(javax.ws.rs.core.MediaType.APPLICATION_JSON)
                .post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public <T> T findAll_XML(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public EstadisticaCampania[] findAll_JSON() throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(EstadisticaCampania[].class);
    }

    public void remove(String id) throws ClientErrorException {
        webTarget.path("remove/"+id).request().delete();
    }

    public void close() {
        client.close();
    }
    
}
