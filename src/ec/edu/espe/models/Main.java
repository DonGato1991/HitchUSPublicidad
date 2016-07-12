/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.models;

import ec.edu.espe.rest.client.CampaniaRestClient;
import ec.edu.espe.rest.client.DetalleCampaniaRestClient;
import ec.edu.espe.rest.client.ElementoRestClient;
import ec.edu.espe.rest.client.EmpresaRestClient;
import ec.edu.espe.rest.client.EstadisticaCampaniaRestClient;
import ec.edu.espe.rest.client.HistorialCampaniaRestClient;
import ec.edu.espe.rest.client.SegmentoDetalleCampaniaRestClient;
import ec.edu.espe.rest.client.TargetEdadRestClient;
import ec.edu.espe.rest.client.UsuarioRestClient;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 *
 * @author homer
 */
public class Main {
    public static void main(String[] args) {
        //UsuarioRestClient client= new  UsuarioRestClient();
        
        probarTargetEdad();
//        
//        Usuario usuario=new Usuario();
//        usuario.setIdUsuario(1);
//        usuario.setNombres("Raul");
//        usuario.setPassword("1213");
//        usuario.setRuc("1234567890");
//        usuario.setCorreoElectronico("fsdfsd@.");
//        client.create_JSON(usuario);
////        //client.remove(5);
////        client.edit_JSON(usuario,1);
////        List<Usuario> usuarios = Arrays.asList(client.findAll_JSON());
////        for (Usuario usuario1 : usuarios) {
////            System.out.println(usuario1.getIdUsuario());
////        }
//        System.err.println(client.find_JSON(usuario.getClass(), 1).getNombres());
        
                
        //probarEmpresas();
        
        //probarElemento();
        //probarTargetEdad();
        //probarCampania();
        //probarEstadisticaCampania();
        //probarHistorial();
        //probarDetalleCampania();
        //probarSegmentoCampania();
    }
    static public void probarEmpresas()
    {
        EmpresaRestClient empcli= new  EmpresaRestClient();
        Empresa empresa= new Empresa();
        empresa.setRuc("1724560592");
        empresa.setDireccion("dir");
        empresa.setEmail("email");
        empresa.setRazonSocial("razonSocial");
        empresa.setRepresentante("representante");
        empresa.setTelefono("telf");
        
        empcli.create_JSON(empresa);
//            List<Empresa> empresas =Arrays.asList (empcli.findAll_JSON());
//            for (Empresa empresa : empresas) {
//                System.out.println(empresa.getRuc());
//        }
    }

    private static void probarTargetEdad() {
       TargetEdadPK targetEdad =new TargetEdadPK();
       targetEdad.setRuc("1724560593");
       //targetEdad.setIdTargetEdad(3);
       TargetEdad target=new TargetEdad();
       target.setTargetEdadPK(targetEdad);
       target.setNombre("Solo Adultos");
       target.setDescripcion("mayore2 18");
       target.setEdadMinima(17);
       target.setEdadMaxima(99);
       target.setGenero("MAS");
       TargetEdadRestClient client =new TargetEdadRestClient();
       client.create_JSON(target);
       //client.remove(targetEdad.toString());
       //client.edit_JSON(target,"ruc=1724560592;idTargetEdad=4");
        //System.out.println(client.find_JSON(target.getClass(),"ruc=1724560592;idTargetEdad=4").getEdadMaxima());
       
        List<TargetEdad> empresas =Arrays.asList (client.findAll_JSON());
            for (TargetEdad empresa : empresas) {
                System.out.println(empresa.getEdadMaxima());
        }
        
    }

    private static void probarElemento() {
        ElementoRestClient client=new ElementoRestClient();
        Elemento elemento=new Elemento();
        elemento.setIdElemento(3);
        elemento.setNombre("elemento");
        elemento.setPath("patch");
        elemento.setPosicion("MOV");
        elemento.setUrl("url");
        client.create_JSON(elemento);
        //client.edit_JSON(elemento, 2);
        //client.remove(2);
        System.out.println("Buscado "+client.find_JSON(elemento.getClass(),3).getPath());
        List<Elemento> elementos =Arrays.asList(client.findAll_JSON());
        for (Elemento elemento1 : elementos) {
            System.out.println(elemento1.getNombre());
        }
    }

    private static void probarCampania() {
       CampaniaPK campaniaPk =new CampaniaPK();
       campaniaPk.setRuc("1724560592");
       campaniaPk.setSecCampania(3);
       Campania target=new Campania();
       target.setCampaniaPK(campaniaPk);
       target.setFechaCreacion(new Date());
       target.setNombre("Solo Panas");
       target.setDescripcion("mayores 18");
       target.setFechaInicio(new Date());
       target.setFechaFin(new Date());
       target.setEstado("A");
       CampaniaRestClient client =new CampaniaRestClient();
       client.create_JSON(target);
       //client.remove("ruc=1724560592;secCampania=3");
       //client.edit_JSON(target,"ruc=1724560592;secCampania=3");
        //System.out.println(client.find_JSON(target.getClass(),"ruc=1724560592;idTargetEdad=4").getEdadMaxima());
       
        List<Campania> empresas =Arrays.asList (client.findAll_JSON());
            for (Campania empresa : empresas) {
                System.out.println(empresa.getNombre());
        }
    }

    private static void probarEstadisticaCampania() {
        EstadisticaCampaniaPK campaniaPk =new EstadisticaCampaniaPK();
       campaniaPk.setRuc("1724560592");
       campaniaPk.setSecCampania(4);
       EstadisticaCampania target=new EstadisticaCampania();
       target.setEstadisticaCampaniaPK(campaniaPk);
       target.setClics(BigInteger.ONE);
       target.setDespliegues(BigInteger.ZERO);
       EstadisticaCampaniaRestClient client =new EstadisticaCampaniaRestClient();
       //client.create_JSON(target);
       //client.remove("ruc=1724560592;secCampania=4");
       //client.edit_JSON(target,"ruc=1724560592;secCampania=4");
        //System.out.println(client.find_JSON(target.getClass(),"ruc=1724560592;idTargetEdad=4").getEdadMaxima());
       
        List<EstadisticaCampania> empresas =Arrays.asList (client.findAll_JSON());
            for (EstadisticaCampania empresa : empresas) {
                System.out.println(empresa.getCampania());
        }
    }

    private static void probarHistorial() {
        HistorialCampania hist= new HistorialCampania();
        hist.setIdHistorialCampania(1);
        CampaniaPK pk=new CampaniaPK();
        pk.setRuc("1724560592");
        pk.setSecCampania(4);
        Campania campania=new Campania();
        campania.setCampaniaPK(pk);
        hist.setCampania(campania);
        hist.setFechaCompra(new Date());
        hist.setClics(BigInteger.ZERO);
        hist.setCostoClic(BigDecimal.ZERO);
        hist.setDespliegues(BigInteger.ZERO);
        hist.setCostoDespliegue(BigDecimal.ZERO);
        HistorialCampaniaRestClient client=new HistorialCampaniaRestClient();
        //client.create_JSON(hist);
        //client.edit_JSON(hist,1);
        //client.remove(1);
        List<HistorialCampania> usuarios = Arrays.asList(client.findAll_JSON());
        for (HistorialCampania usuario1 : usuarios) {
            System.out.println(usuario1.getCostoDespliegue());
        }
    }

    private static void probarDetalleCampania() { 
       DetalleCampaniaPK campaniaPk =new DetalleCampaniaPK();
       campaniaPk.setRuc("1724560592");
       campaniaPk.setSecCampania(4);
       campaniaPk.setIdElemento(1);
       DetalleCampania target=new DetalleCampania();
       target.setDetalleCampaniaPK(campaniaPk);
       target.setModoFacturacion("EFE");
       target.setDespliegues(BigInteger.TEN);
       target.setClics(BigInteger.ONE);
       
       DetalleCampaniaRestClient client =new DetalleCampaniaRestClient();
       //client.create_JSON(target);
       //client.remove("ruc=1724560592;secCampania=4;idElemento=1");
       //client.edit_JSON(target,"ruc=1724560592;secCampania=4;idElemento=1");
        System.out.println(client.find_JSON(target.getClass(),"ruc=1724560592;secCampania=4;idElemento=1").getClics());
       
        List<DetalleCampania> empresas =Arrays.asList (client.findAll_JSON());
            for (DetalleCampania empresa : empresas) {
                System.out.println(empresa.getClics());
        }
    }

    private static void probarSegmentoCampania() {
        SegmentoDetalleCampaniaPK pk=new SegmentoDetalleCampaniaPK();
        pk.setIdElemento(1);
        pk.setIdTargetEdad(1);
        pk.setRuc("1724560592");
        pk.setSecCampania(4);
        pk.setTarRuc("1724560592");
        SegmentoDetalleCampania campania=new SegmentoDetalleCampania();
        campania.setSegmentoDetalleCampaniaPK(pk);
        campania.setHoraFin("18:00");
        campania.setHoraInicio("18:00");
        campania.setMaximoHora("18:00");
        campania.setMinimoHora("18:00");
        SegmentoDetalleCampaniaRestClient client=new SegmentoDetalleCampaniaRestClient();
        //client.create_JSON(campania);
        //client.edit_JSON(campania,"ruc=1724560592;secCampania=4;idElemento=1;tarRuc=1724560592;idTargetEdad=1");
        //client.remove("ruc=1724560592;secCampania=4;idElemento=1;tarRuc=1724560592;idTargetEdad=1");
        System.out.println(client.find_JSON(campania.getClass(), "ruc=1724560592;secCampania=4;idElemento=1;tarRuc=1724560592;idTargetEdad=1"));
        List<SegmentoDetalleCampania> segmentos=Arrays.asList(client.findAll_JSON());
        for (SegmentoDetalleCampania segmento : segmentos) {
            System.out.println(segmento.getHoraFin());
        }
    }
}
