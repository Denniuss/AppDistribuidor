package com.newmantech.appdistribuidor.utils;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilitario {

    public static String baseUrl = "http://192.168.1.61:8077/";
    //public static String baseUrl = "http://10.21.148.9:8077/";

    public static Integer idDistriduidor = 34;

    public static boolean isInteger(String pNumber)
    {
        try {
            double vDecimal = Double.parseDouble(pNumber);
            if(vDecimal == (int) vDecimal){
                return true;
            }else{
                return false;
            }
        }catch (NumberFormatException ex){
            return false;
        }
    }

    public static boolean isNumeric(String pNumber)
    {
        try {
            double number = Double.parseDouble(pNumber);
            return true;
        }catch (NumberFormatException ex){
            return false;
        }
    }

    public static boolean isEmail(String pEmail) {
        //^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,6}$
        Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(pEmail);
        return matcher.find();
    }

    public static double roundDecimals(double d, int decimals)
    {
        String pattern = "#0.";
        for(int i=0; i<decimals; i++){
            pattern = pattern + "#";
        }
        DecimalFormat twoDForm = new DecimalFormat(pattern);
        return Double.valueOf(twoDForm.format(d));
    }

    public enum ESTADO_FLUJO_TRABAJO{
        CREACION_FLUJO (100,"CREACION FLUJO","Se está procesando su pedido"),
        APROBACION_FLUJO (101,"APROBACION FLUJO","Se está procesando su pedido"),
        ABASTECIMIENTO_FLUJO (102,"ABASTECIMIENTO FLUJO","En preparación"),

        PRODUCCION_FLUJO (103,"PRODUCCION FLUJO","En preparación"),
        CONTROL_CALIDAD_FLUJO (104,"CONTROL CALIDAD FLUJO","En preparación"),
        PRE_DISTRIBUCION_FLUJO (105,"PRE DISTRIBUCION FLUJO","Pedido programado para su distribución"),

        INCIO_ENTREGA_REPROGRAMAOD (106,"INICIO ENTREGA REPROGRAMADO","Pedido re-programado para su distribución"),

        INCIO_ENTREGA_FLUJO (107,"INICIO ENTREGA FLUJO","Pedido enviado"),
        INCIDENCIA_FLUJO (108,"INCIDENCIA FLUJO","Se ha registrado una incidencia en la entrega"),

        FIN_ENTREGA_FLUJO (109,"FIN ENTREGA FLUJO","Pedido entregado"),

        INCIO_ENTREGA_FLUJO_POST_INCIDENCIA (110,"ENTREGA","Pedido entregado"),
        FIN_ENTREGA_FLUJO_POST_INCIDENCIA (111,"ENTREGA","Pedido entregado ");

        private final int codigo;
        private final String  texto;
        private final String keyMsg;

        private ESTADO_FLUJO_TRABAJO(int codigo, String texto, String keyMsg){
            this.codigo = codigo;
            this.texto = texto;
            this.keyMsg= keyMsg;
        }
        public int getCodigo() {
            return codigo;
        }
        public String getTexto() {
            return texto;
        }
        public String getKeyMsg() {
            return keyMsg;
        }

        public static ESTADO_FLUJO_TRABAJO getEstadoWorkFlowByID(Integer id){
            ESTADO_FLUJO_TRABAJO[] valores = ESTADO_FLUJO_TRABAJO.values();
            for(int i=0; i<valores.length; i++){
                if(valores[i].getCodigo() == id){
                    return  valores[i];
                }
            }
            return null;
        }

        public static String getEstadoWorkFlowKeyMsg(Integer estado){
            String descripcion =  null;
            for(Utilitario.ESTADO_FLUJO_TRABAJO item : Utilitario.ESTADO_FLUJO_TRABAJO.values() ){
                if(item.getCodigo() ==estado){
                    descripcion = item.getKeyMsg();
                    break;
                }

            }
            return descripcion;
        }
    }


    public  enum ESTADO_PEDIDO_SIMPLE{
        CREADO_PENDIENTE_APROBACION_PEDIDO (200,"CREADO_PENDIENTE_APROBACION_PEDIDO","CREADO_PENDIENTE_APROBACION_PEDIDO"),
        APROBADO_PENDIENTE_ABASTECIMIENTO_PEDIDO (201,"APROBADO_PENDIENTE_ABASTECIMIENTO_PEDIDO","APROBADO_PENDIENTE_ABASTECIMIENTO_PEDIDO"),
        ABASTECIMIENTO_PENDIENTE_PRODUCCION_PEDIDO (202,"ABASTECIMIENTO_PENDIENTE_PRODUCCION_PEDIDO","ABASTECIMIENTO_PENDIENTE_PRODUCCION_PEDIDO"),

        //	PRODUCIDO_PENDIENTE_CONTROL_CALIDAD_PEDIDO (203,"PRODUCIDO PENDIENTE CONTROL CALIDAD","PRODUCIDO PENDIENTE CONTROL CALIDAD"),
        /*Comentado porque en los estados del Flujo se comentara la produccion*/
        CONTROLADO_PENDIENTE_PRE_DISTRIBUCION_PEDIDO (204,"CONTROLADO PRE DISTRIBUCION","CONTROLADO PRE DISTRIBUCION"),
        PRE_DISTRIBUIDO_PEDIDO (205,"PRE DISTRIBUIDO","PRE DISTRIBUIDO"),
        RE_PROGRAMADO (206,"RE PROGRAMADO","RE PROGRAMADO"),//TODO

        CON_INCIDENCIA_PEDIDO (207,"INCIDENCIA","INCIDENCIA"),

        DISTRIBUIDO_INICIADO_PEDIDO (208,"DISTRIBUIDO INICIADO","DISTRIBUIDO INICIADO"),
        DISTRIBUIDO_FINALIZADO_PEDIDO (209,"DISTRIBUIDO FINALIZADO","DISTRIBUIDO FINALIZADO"),

        INICIO_PEDIDO_POST_INCIDENCIA (210,"DISTRIBUIDO INICIADO","DISTRIBUIDO INICIADO"),
        FIN_PEDIDO_POST_INCIDENCIA (211,"DISTRIBUIDO FINALIZADO","DISTRIBUIDO FINALIZADO");

        private final int codigo;
        private final String  texto;
        private final String keyMsg;

        private ESTADO_PEDIDO_SIMPLE (int codigo, String texto, String keyMsg){
            this.codigo = codigo;
            this.texto = texto;
            this.keyMsg= keyMsg;
        }
        public int getCodigo() {
            return codigo;
        }
        public String getTexto() {
            return texto;
        }
        public String getKeyMsg() {
            return keyMsg;
        }

        public static ESTADO_PEDIDO_SIMPLE getEstadoPedidoByID(Integer id){
            ESTADO_PEDIDO_SIMPLE[] valores = ESTADO_PEDIDO_SIMPLE.values();
            for(int i=0; i<valores.length; i++){
                if(valores[i].getCodigo() == id){
                    return  valores[i];
                }
            }
            return null;
        }

        public static String getEstadoPedidoKeyMsg(Integer estado){
            String descripcion =  null;
            for(ESTADO_PEDIDO_SIMPLE item : ESTADO_PEDIDO_SIMPLE.values() ){
                if(item.getCodigo() ==estado){
                    descripcion = item.getKeyMsg();
                    break;
                }

            }
            return descripcion;
        }
    }

}
