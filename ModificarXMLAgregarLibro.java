import java.io.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

public class ModificarXMLAgregarLibro {
    public  void creadorxml(String nombre, String telefono, String correo, String destino, String producto1, String producto2, String producto3, String producto4, String producto5){
        try {
            // Definir los datos del nuevo libro
            String nuevoNombre = nombre;
            String nuevoTelefono = telefono;
            String nuevoCorreo = correo;
            String nuevoDestino = destino;
            String nuevoProducto1 = producto1;
            String nuevoProducto2 = producto2;
            String nuevoProducto3 = producto3;
            String nuevoProducto4 = producto4;
            String nuevoProducto5 = producto5;

            // Cargar el archivo XML existente
            File xmlFile = new File("Rapitrack.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();

            // Obtener la raíz <Clientes>
            Element root = document.getDocumentElement();

            // Crear un nuevo nodo <Cliente>
            Element Cliente = document.createElement("Cliente");
            // Crear el nodo <titulo> y asignar el valor
            Element Nombre = document.createElement("Nombre");
            Nombre.setTextContent(nuevoNombre);
            Cliente.appendChild(Nombre);

            // Crear el nodo <Telefono> y asignar el valor
            Element Telefono = document.createElement("Telefono");
            Telefono.setTextContent(nuevoTelefono);
            Cliente.appendChild(Telefono);

            // Crear el nodo <Correo> y asignar el valor
            Element Correo = document.createElement("Correo");
            Correo.setTextContent(nuevoCorreo);
            Cliente.appendChild(Correo);

            // Crear el nodo <Destino> y asignar el valor
            Element Destino = document.createElement("Destino");
            Destino.setTextContent(nuevoDestino);
            Cliente.appendChild(Destino);

            // Crear el nodo <P1> y asignar el valor
            if(nuevoProducto1.equals("Cantidad:") == false){
            Element P1 = document.createElement("iPhone15Pro");
            P1.setTextContent(nuevoProducto1);
            Cliente.appendChild(P1);
            }
             // Crear el nodo <P2> y asignar el valor
             if(nuevoProducto2.equals("Cantidad:") == false){
             Element P2 = document.createElement("Samsung2Pro");
             P2.setTextContent(nuevoProducto2);
             Cliente.appendChild(P2);
             }
             // Crear el nodo <P3> y asignar el valor
             if(nuevoProducto3.equals("Cantidad:") == false){
             Element P3 = document.createElement("NikeTShirt");
             P3.setTextContent(nuevoProducto3);
             Cliente.appendChild(P3);
             }
             // Crear el nodo <P4> y asignar el valor
             if(nuevoProducto4.equals("Cantidad:") == false){
             Element P4 = document.createElement("MacBookPro14");
             P4.setTextContent(nuevoProducto4);
             Cliente.appendChild(P4);
             }
             // Crear el nodo <P5> y asignar el valor
             if(nuevoProducto5.equals("Cantidad:") == false){
             Element P5 = document.createElement("AdidasUltraboost");
             P5.setTextContent(nuevoProducto5);
             Cliente.appendChild(P5);
             }
            // Agregar el nuevo nodo <Cliente> a <Clientes>
            root.appendChild(Cliente);

            // Guardar los cambios en el archivo XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(xmlFile);
            transformer.transform(source, result);

            System.out.println("Nuevo cliente añadido exitosamente al XML.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}