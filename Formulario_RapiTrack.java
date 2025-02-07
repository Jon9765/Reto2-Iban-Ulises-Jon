import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.*;
import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
class Formulario_RapiTrack {
    
   
    public static void main(String[] args) {

        ModificarXMLAgregarLibro xml = new ModificarXMLAgregarLibro();
        //Clase para hacer las variables global para poder usar las en el metodo ItemListener
       class Global{
            public static Boolean box1_value = false;
            public static Boolean box2_value = false;
            public static Boolean box3_value = false;
            public static Boolean box4_value = false;
            public static Boolean box5_value = false;
            public static Boolean condiciones_value = false;
            public static String  nombre_value = "";
            public static String telefono_value = "";
            public static String correo_value = "";
            public static String destino_value;
            public static int cantidad_box1;
            public static int cantidad_box2;
            public static int cantidad_box3;
            public static int cantidad_box4;
            public static int cantidad_box5;
            public static int id;
            public static int id_pedido;
            public static LocalDate fechaActual = LocalDate.now();
            public static LocalDate fechaentrega = fechaActual.plusDays(10);
          
        }
      

        SwingUtilities.invokeLater(() -> {

        // Declarando la ventana y su panel
        JFrame frame = new JFrame("RapiTrack");
        JPanel panel = new JPanel();
        
        // Cargar el icono correctamente
        ImageIcon icono = new ImageIcon(Formulario_RapiTrack.class.getResource("/rapitrack.jpeg"));
        frame.setIconImage(icono.getImage());

        //Usamos groupLayout para poder colocar(label,texfield,botones y checbox) en filas y columnas
        GroupLayout grupo = new GroupLayout(panel);
        panel.setLayout(grupo);


        //Primera columna de informacion del nombre
        JLabel l_nombre = new JLabel("Ingrese su nombre:");
        JTextField texto_nombre = new JTextField(20);

        //Ajuste de tamaño del texfield
        texto_nombre.setMinimumSize(new Dimension(150, 25));
        texto_nombre.setPreferredSize(new Dimension(150, 25));
        texto_nombre.setMaximumSize(new Dimension(150, 25));
        
        //Segunda columna de informacion del telefono
        JLabel l_telefono = new JLabel("Telefono:");
        JTextField texto_telefono = new JTextField(9);
        
        //Ajuste de tamaño del texfield
        texto_telefono.setMinimumSize(new Dimension(150, 25));
        texto_telefono.setPreferredSize(new Dimension(150, 25));
        texto_telefono.setMaximumSize(new Dimension(150, 25));

        //Tecera columna de informacion del correo
        JLabel l_correo = new JLabel("Ingrese su Correo:");
        JTextField texto_correo = new JTextField(20);

        //Ajuste de tamaño del texfield
        texto_correo.setMinimumSize(new Dimension(150, 25));
        texto_correo.setPreferredSize(new Dimension(150, 25));
        texto_correo.setMaximumSize(new Dimension(150, 25));

        //Cuarta columna de informacion del destino
        JLabel l_destino = new JLabel("Ingrese su Destino:");
        JTextField texto_destino = new JTextField(20);
        
        //Ajuste de tamaño del texfield
        texto_destino.setMinimumSize(new Dimension(150, 25));
        texto_destino.setPreferredSize(new Dimension(150, 25));
        texto_destino.setMaximumSize(new Dimension(150, 25));

        //Cinco columnas ordenadas con un checkbox y su texfield para ingreasar 
        JCheckBox Box1 = new JCheckBox("iPhone 15 Pro");
        JTextField texto_Box1 = new JTextField("Cantidad:",9);

        //Ajuste de tamaño del texfield
        texto_Box1.setMinimumSize(new Dimension(150, 25));
        texto_Box1.setPreferredSize(new Dimension(150, 25));
        texto_Box1.setMaximumSize(new Dimension(150, 25));

        //Cinco columnas ordenadas con un checkbox y su texfield para ingreasar
        JCheckBox Box2 = new JCheckBox("Samsung Galaxy Buds 2 Pro");
        JTextField texto_Box2 = new JTextField("Cantidad:",9);

        //Ajuste de tamaño del texfield
        texto_Box2.setMinimumSize(new Dimension(150, 25));
        texto_Box2.setPreferredSize(new Dimension(150, 25));
        texto_Box2.setMaximumSize(new Dimension(150, 25));

        //Cinco columnas ordenadas con un checkbox y su texfield para ingreasar
        JCheckBox Box3 = new JCheckBox("Nike Air Max 90 T-Shirt");
        JTextField texto_Box3 = new JTextField("Cantidad:",9);

        //Ajuste de tamaño del texfield
        texto_Box3.setMinimumSize(new Dimension(150, 25));
        texto_Box3.setPreferredSize(new Dimension(150, 25));
        texto_Box3.setMaximumSize(new Dimension(150, 25));

        //Cinco columnas ordenadas con un checkbox y su texfield para ingreasar
        JCheckBox Box4 = new JCheckBox("MacBook Pro 14\" (M2 Pro)");
        JTextField texto_Box4 = new JTextField("Cantidad:",9);

        //Ajuste de tamaño del texfield
        texto_Box4.setMinimumSize(new Dimension(150, 25));
        texto_Box4.setPreferredSize(new Dimension(150, 25));
        texto_Box4.setMaximumSize(new Dimension(150, 25));

        //Cinco columnas ordenadas con un checkbox y su texfield para ingreasar
        JCheckBox Box5 = new JCheckBox("Adidas Ultraboost 23");
        JTextField texto_Box5 = new JTextField("Cantidad:",9);

        //Ajuste de tamaño del texfield
        texto_Box5.setMinimumSize(new Dimension(150, 25));
        texto_Box5.setPreferredSize(new Dimension(150, 25));
        texto_Box5.setMaximumSize(new Dimension(150, 25));

        //Checkbox para aceptar condiciones y terminos
        JCheckBox condiciones = new JCheckBox("Acepto Terminos y Condciones...");

        //Boton para enivar el formulario a (Base de datos y XMl)
        JButton aceptar = new JButton("Enviar");

        //Boton para volver al menu principal
        JButton menu = new JButton("Volver Atras");

        //Modo automatico para generar espcacios entre las agrupaciones
        grupo.setAutoCreateGaps(true);
        grupo.setAutoCreateContainerGaps(true);

   
        //Agrupacion en Horizontal
        grupo.setHorizontalGroup(
            grupo.createSequentialGroup()
                .addGroup(grupo.createParallelGroup()
                    .addComponent(l_nombre)
                    .addComponent(l_telefono)
                    .addComponent(l_correo)
                    .addComponent(l_destino)
                    .addComponent(Box1)
                    .addComponent(Box2)
                    .addComponent(Box3)
                    .addComponent(Box4)
                    .addComponent(Box5)
                    .addComponent(condiciones)
                    .addComponent(menu)
                    )
                .addGroup(grupo.createParallelGroup()
                    .addComponent(texto_nombre)
                    .addComponent(texto_telefono)
                    .addComponent(texto_correo)
                    .addComponent(texto_destino)
                    .addComponent(texto_Box1)
                    .addComponent(texto_Box2)
                    .addComponent(texto_Box3)
                    .addComponent(texto_Box4)
                    .addComponent(texto_Box5)
                    .addComponent(aceptar)
                    )
             
                
        );
        //Agrupacion en vertical
        grupo.setVerticalGroup(
            grupo.createSequentialGroup()
                .addGroup(grupo.createParallelGroup()
                    .addComponent(l_nombre)
                    .addComponent(texto_nombre))
                .addGroup(grupo.createParallelGroup()
                    .addComponent(l_telefono)
                    .addComponent(texto_telefono))
                .addGroup(grupo.createParallelGroup()
                    .addComponent(l_correo)
                    .addComponent(texto_correo))
                    .addGroup(grupo.createParallelGroup()
                    .addComponent(l_destino)
                    .addComponent(texto_destino))
                    .addGroup(grupo.createParallelGroup()
                    .addComponent(Box1)
                    .addComponent(texto_Box1)
                    )
                    .addGroup(grupo.createParallelGroup()
                    .addComponent(Box2)
                    .addComponent(texto_Box2)
                    )
                    .addGroup(grupo.createParallelGroup()
                    .addComponent(Box3)
                    .addComponent(texto_Box1).addComponent(texto_Box3)
                    )
                    .addGroup(grupo.createParallelGroup()
                    .addComponent(Box4)
                    .addComponent(texto_Box4)
                    )
                    .addGroup(grupo.createParallelGroup()
                    .addComponent(Box5)
                    .addComponent(texto_Box5)
                    )
                    .addGroup(grupo.createParallelGroup()
                    .addComponent(condiciones)
                    .addComponent(aceptar)
                    )
                    .addGroup(grupo.createParallelGroup()
                    .addComponent(menu))
                    
                
        );
        
        //Ventana de usuario que no es visible hasta usar el boton usuario desde el menu
        frame.add(panel);
        frame.setSize(450, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(false);

    


        //Frame de inicio para escoger modo usuario o administrativo
        JFrame index = new JFrame("RapiTrack");
        JPanel pindex = new JPanel();
        JButton Admin = new JButton("Administrador");
        JButton Usur = new JButton("usuario");
        index.setIconImage(icono.getImage());
         pindex.add(Admin);
         pindex.add(Usur);
        index.setLocationRelativeTo(null);
        index.setSize(350, 120);
        index.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        index.setVisible(true);
        index.add(pindex);

        //Metodo para pasar al panel de usuario
        Usur.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                index.setVisible(false);
                frame.setVisible(true);
            }
        });

        //Meotodo para pasar al panel index
        menu.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                index.setVisible(true);
                frame.setVisible(false);
            }
        });

        //Metodo para retirar el texto informativo para el usuario pueda meter la cantidad del producto
        texto_Box1.addFocusListener(new FocusListener() {
            @Override
        public void focusGained(FocusEvent e) {
            texto_Box1.setText("");
        }
        public void focusLost(FocusEvent e) {
            if (texto_Box1.getText().isBlank())
                 texto_Box1.setText("Cantidad:");
        }
            
        });

        //Metodo para retirar el texto informativo para el usuario pueda meter la cantidad del producto
        texto_Box2.addFocusListener(new FocusListener() {
            @Override
        public void focusGained(FocusEvent e) {
            texto_Box2.setText("");
        }
        public void focusLost(FocusEvent e) {
            if (texto_Box2.getText().isBlank())
                 texto_Box2.setText("Cantidad:");
        }
            
        });

        //Metodo para retirar el texto informativo para el usuario pueda meter la cantidad del producto
        texto_Box3.addFocusListener(new FocusListener() {
            @Override
        public void focusGained(FocusEvent e) {
            texto_Box3.setText("");
        }
        public void focusLost(FocusEvent e) {
            if (texto_Box3.getText().isBlank())
                 texto_Box3.setText("Cantidad:");
        }
            
        });

        //Metodo para retirar el texto informativo para el usuario pueda meter la cantidad del producto
        texto_Box4.addFocusListener(new FocusListener() {
            @Override
        public void focusGained(FocusEvent e) {
            texto_Box4.setText("");
        }
        public void focusLost(FocusEvent e) {
            if (texto_Box4.getText().isBlank())
                 texto_Box4.setText("Cantidad:");
        }
            
        });

        //Metodo para retirar el texto informativo para el usuario pueda meter la cantidad del producto
        texto_Box5.addFocusListener(new FocusListener() {
            @Override
        public void focusGained(FocusEvent e) {
            texto_Box5.setText("");
        }
        public void focusLost(FocusEvent e) {
            if (texto_Box5.getText().isBlank())
                 texto_Box5.setText("Cantidad:");
        }  
        });


        //Declaracion de inicio de sesion administrativo
        JFrame sesion = new JFrame("Rapitrack");
        sesion.setIconImage(icono.getImage());
        JPanel psesion = new JPanel();
        JTextField trabaja = new JTextField("Usuario:",9);

        //Ajuste de tamaño del texfield
        trabaja.setMinimumSize(new Dimension(150, 25));
        trabaja.setPreferredSize(new Dimension(150, 25));
        trabaja.setMaximumSize(new Dimension(150, 25));
        JTextField contraseña = new JTextField("Contraseña:",9);

         //Ajuste de tamaño del texfield
        contraseña.setMinimumSize(new Dimension(150, 25));
        contraseña.setPreferredSize(new Dimension(150, 25));
        contraseña.setMaximumSize(new Dimension(150, 25));
        JButton comprueba = new JButton("Iniciar");
        JButton retirada = new JButton("Menu");
        
        //Usamos groupLayout para poder colocar los texfield en columnas
        GroupLayout grupo2 = new GroupLayout(psesion);
        psesion.setLayout(grupo2);
        
        //Modo automatico para generar espcacios entre las agrupaciones
        grupo2.setAutoCreateGaps(true);
        grupo2.setAutoCreateContainerGaps(true);

        //Agrupacion en Horizontal
        grupo2.setHorizontalGroup(
            grupo2.createSequentialGroup()
            .addGroup(grupo2.createParallelGroup()
                .addComponent(trabaja)
                .addComponent(contraseña)
                .addComponent(comprueba))
            .addGroup(grupo2.createParallelGroup()
                .addComponent(retirada))
        );

        //Agrupacion en Vertical
        grupo2.setVerticalGroup(
            grupo2.createSequentialGroup()
            .addGroup(grupo2.createParallelGroup()
                .addComponent(trabaja))
            .addGroup(grupo2.createParallelGroup()
                .addComponent(contraseña))
            .addGroup(grupo2.createParallelGroup()
                .addComponent(comprueba)
                .addComponent(retirada))
        );
        


        //Frame donde en usuario tiene que insertar sus datos para iniciar sesion
        sesion.setLocationRelativeTo(null);
        sesion.setSize(250, 160);
        sesion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sesion.setVisible(false);
        sesion.add(psesion);

        

         //Declaracion del frame administrativo
        JFrame adminJFrame = new JFrame("Rapitrack");
        adminJFrame.setIconImage(icono.getImage());
        JPanel botoncitos = new JPanel();
        JPanel pclientes = new JPanel();
            pclientes.setLayout(new BorderLayout());
        JPanel pproductos = new JPanel();
            pproductos.setLayout(new BorderLayout());
        JPanel pproveedores = new JPanel();
        pproveedores.setLayout(new BorderLayout());
        JTextField busquedaId = new JTextField("Id",3);
        JButton cboton = new JButton("CLIENTES");
        JButton prboton = new JButton("PRODUCTOS");
        JButton pvboton = new JButton("PROVEEDORES");
        JButton volver = new JButton("ATRAS");
        JButton volver2 = new JButton("ATRAS");
        JButton busqueda = new JButton("BUSCAR");
        JButton busqueda2 = new JButton("ACTUALIZAR");
        JButton inicio = new JButton ("MENU");
        JPanel cambiostock = new JPanel();
            cambiostock.setLayout(new FlowLayout());
        JTextField busquedaId2 = new JTextField("Id",3);
        JTextField cantidadcambio = new JTextField("Stock",5);
        JPanel panelshouth = new JPanel();
            panelshouth.setLayout(new FlowLayout());
        JPanel atrassouth = new JPanel();
            atrassouth.setLayout(new FlowLayout());
        JPanel volversouth = new JPanel();
        volversouth.setLayout(new FlowLayout());
        JPanel botones = new JPanel();
            botones.setLayout(new FlowLayout());
        JTextField busquedaId3 = new JTextField("Id",3);
        JButton busqueda3 = new JButton("BUSCAR");
        JButton volver3 = new JButton ("ATRAS");
        JButton meter = new JButton("INSERTAR NUEVO PROVEEDOR");
        volversouth.add(busquedaId3);
        volversouth.add(busqueda3);
        volversouth.add(meter);
        volversouth.add(volver3);
        

        botoncitos.setLayout(new BorderLayout());
        botoncitos.add(botones,BorderLayout.NORTH);
        atrassouth.add(inicio);
        botones.add(cboton);
        botones.add(prboton);
        botones.add(pvboton);
        botoncitos.add(atrassouth,BorderLayout.SOUTH);
        adminJFrame.add(botoncitos);
        
        pclientes.add(panelshouth,BorderLayout.SOUTH);
            panelshouth.add(busquedaId);
            panelshouth.add(busqueda);
            panelshouth.add(volver);
       
       cambiostock.add(busquedaId2);
       cambiostock.add(cantidadcambio);
       cambiostock.add(busqueda2);
       cambiostock.add(volver2);

        adminJFrame.setLocationRelativeTo(null);
        adminJFrame.setSize(400, 120);
        adminJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminJFrame.setVisible(false);

          //Tabla para mostrar datos clientes
          DefaultTableModel modelo = new DefaultTableModel();
          JTable tabla = new JTable(modelo);
          JScrollPane scrollPane = new JScrollPane(tabla);
  
          //Tabla para mostrar datos productos
          DefaultTableModel modelo2 = new DefaultTableModel();
          JTable tabla2 = new JTable(modelo2);
          JScrollPane scrollPane2 = new JScrollPane(tabla2);
  
           //Tabla para mostrar datos proveedores
           DefaultTableModel modelo3 = new DefaultTableModel();
           JTable tabla3 = new JTable(modelo3);
           JScrollPane scrollPane3 = new JScrollPane(tabla3);
           pclientes.add(scrollPane,BorderLayout.CENTER);

            
            JButton recular = new JButton("ATRAS");
            JPanel proveer_nuevo = new JPanel ();
            proveer_nuevo.setLayout(new BorderLayout());
            JPanel centrado_nuevo = new JPanel();
            centrado_nuevo.setLayout(new FlowLayout());
            JPanel sur_nuevo = new JPanel();
            sur_nuevo.setLayout(new FlowLayout());
            JPanel nor_nuevo = new JPanel();
            nor_nuevo.setLayout(new FlowLayout());
            JTextField NProveedor = new JTextField("Nombre",10);
            JTextField localizazio = new JTextField("Localidad",10);
            JButton sumar_Proveedor= new JButton("INSERTAR");
            JTextField sumar_idprV = new JTextField("Id preveedor");
            JTextField sumar_idpro = new JTextField("Id producto");
            JTextField sumar_precio = new JTextField("Precio");
            JButton sumar_Prove= new JButton("INSERTAR");
            nor_nuevo.add(sumar_idprV);
            nor_nuevo.add(sumar_idpro);
            nor_nuevo.add(sumar_precio);
            nor_nuevo.add(sumar_Prove);
            centrado_nuevo.add(NProveedor);
            centrado_nuevo.add(localizazio);
            centrado_nuevo.add(sumar_Proveedor);
            sur_nuevo.add(recular);
            proveer_nuevo.add(nor_nuevo,BorderLayout.NORTH);
            proveer_nuevo.add(centrado_nuevo,BorderLayout.CENTER);
            proveer_nuevo.add(sur_nuevo,BorderLayout.SOUTH);


sumar_precio.addFocusListener(new FocusListener() {
            @Override
        public void focusGained(FocusEvent e) {
            sumar_precio.setText("");
        }
        public void focusLost(FocusEvent e) {
            if (sumar_precio.getText().isBlank())
                 sumar_precio.setText("Precio");
        }  
        });

busquedaId3.addFocusListener(new FocusListener() {
            @Override
        public void focusGained(FocusEvent e) {
            busquedaId3.setText("");
        }
        public void focusLost(FocusEvent e) {
            if (busquedaId3.getText().isBlank())
                 busquedaId3.setText("Id");
        }  
        });
        
        
busquedaId2.addFocusListener(new FocusListener() {
            @Override
        public void focusGained(FocusEvent e) {
            busquedaId2.setText("");
            
        }
        public void focusLost(FocusEvent e) {
            if (busquedaId2.getText().isBlank())
                 busquedaId2.setText("Id");
        }  
        });

cantidadcambio.addFocusListener(new FocusListener() {
            @Override
        public void focusGained(FocusEvent e) {
            cantidadcambio.setText("");
        }
        public void focusLost(FocusEvent e) {
            if (cantidadcambio.getText().isBlank())
                 cantidadcambio.setText("Stock");
        }  
        });
busquedaId.addFocusListener(new FocusListener() {
            @Override
        public void focusGained(FocusEvent e) {
            busquedaId.setText("");
        }
        public void focusLost(FocusEvent e) {
            if (busquedaId.getText().isBlank())
                 busquedaId.setText("Id");
        }  
        });

        
sumar_idpro.addFocusListener(new FocusListener() {
            @Override
        public void focusGained(FocusEvent e) {
            sumar_idpro.setText("");
        }
        public void focusLost(FocusEvent e) {
            if (sumar_idpro.getText().isBlank())
                 sumar_idpro.setText("Id producto");
        }  
        });

sumar_idprV.addFocusListener(new FocusListener() {
            @Override
        public void focusGained(FocusEvent e) {
            sumar_idprV.setText("");
        }
        public void focusLost(FocusEvent e) {
            if (sumar_idprV.getText().isBlank())
                 sumar_idprV.setText("Id preveedor");
        }  
        });

NProveedor.addFocusListener(new FocusListener() {
            @Override
        public void focusGained(FocusEvent e) {
            NProveedor.setText("");
        }
        public void focusLost(FocusEvent e) {
            if (NProveedor.getText().isBlank())
                 NProveedor.setText("Nombre");
        }  
        });
 
 localizazio.addFocusListener(new FocusListener() {
            @Override
        public void focusGained(FocusEvent e) {
            localizazio.setText("");
        }
        public void focusLost(FocusEvent e) {
            if (localizazio.getText().isBlank())
                 localizazio.setText("Localidad");
        }  
        });


        //Metodo buscar el id clientes y encontrar sus pedidos
        busqueda.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                ADcliente("Select * from Pedidos where Id_Cliente = '" + busquedaId.getText() + "';", modelo);
            }
        });

        //Metodo para cambiar el stock de los productos
        busqueda2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                updates("Update Productos set Stock = '" + cantidadcambio.getText() + "'where Id_Producto = '" + busquedaId2.getText() + "';");
                ADcliente("Select * from Productos;", modelo2);
            }
        });

        //Metodo buscar que proveen los proveedores
        busqueda3.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                ADcliente("Select Id_Producto,Id_Proveedor,Precio_compra from Proveer where Id_Proveedor = '" + busquedaId3.getText() + "';", modelo3);
            }
        });



         //Metodo para pasar al frame de inicio sesion
         Admin.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                index.setVisible(false);
                sesion.setVisible(true);
            }
        });

         //Metodo para pasar al frame index
         retirada.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                sesion.setVisible(false);
                index.setVisible(true);
                
            }
        });
         //Metodo para pasar al frame index
         inicio.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                adminJFrame.setVisible(false);
                index.setVisible(true);
                
            }
        });


        //Metodo para retirar el texto informativo para el usuario pueda meter el usuario
        trabaja.addFocusListener(new FocusListener() {
            @Override
        public void focusGained(FocusEvent e) {
            trabaja.setText("");
        }
        public void focusLost(FocusEvent e) {
            if (trabaja.getText().isBlank())
                 trabaja.setText("Usuario:");
        }  
        });
        
        //Metodo para retirar el texto informativo para el usuario pueda meter la contraseña
        contraseña.addFocusListener(new FocusListener() {
            @Override
        public void focusGained(FocusEvent e) {
            contraseña.setText("");
        }
        public void focusLost(FocusEvent e) {
            if (contraseña.getText().isBlank())
                 contraseña.setText("Contraseña:");
        }  
        }); 


        //Metodo para pasar al panel administrativo
        comprueba.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(trabaja.getText().equalsIgnoreCase("fran") && contraseña.getText().equalsIgnoreCase("moltres")){
                    sesion.setVisible(false);
                    adminJFrame.setVisible(true);
                }else{
                    trabaja.setText("Usuario:");
                    contraseña.setText("Contraseña:");      
            }
        }});
        
           //Metodo para cambiar al panel de cliente
           cboton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                pclientes.setVisible(true);
                botoncitos.setVisible(false);
                adminJFrame.add(pclientes);
                adminJFrame.setSize(400,450);
                ADcliente("Select * from Clientes;", modelo);
                
            }
        });

        meter.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                pproveedores.setVisible(false);
                proveer_nuevo.setVisible(true);
                
                adminJFrame.add(proveer_nuevo);
                adminJFrame.setSize(400,150);
            }
        });
           //Metodo para cambiar al panel de productos
           prboton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                pproductos.setVisible(true);
                botoncitos.setVisible(false);
                pproveedores.setVisible(false);
                adminJFrame.add(pproductos);
                pproductos.add(scrollPane2,BorderLayout.CENTER);
                pproductos.add(cambiostock,BorderLayout.SOUTH);
                adminJFrame.setSize(600,200);
                ADcliente("Select * from Productos;", modelo2);
                
            }
        });

        //Metodo para cambiar al panel de proveedores
        pvboton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                pproveedores.setVisible(true);
                botoncitos.setVisible(false);
                adminJFrame.add(pproveedores);
                pproveedores.add(scrollPane3,BorderLayout.CENTER);
                pproveedores.add(volversouth,BorderLayout.SOUTH);
                adminJFrame.setSize(600,200);
                ADcliente("Select * from Proveedores;", modelo3);
                
            }
        });



         //Metodo para cambiar al panel de boncitos
         volver.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                pclientes.setVisible(false);
                pproductos.setVisible(false);
                pproveedores.setVisible(false);
                botoncitos.setVisible(true);
                adminJFrame.setSize(400,120);
                
            }
        });

         //Metodo para cambiar al panel de boncitos
         volver2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                pclientes.setVisible(false);
                pproductos.setVisible(false);
                botoncitos.setVisible(true);
                adminJFrame.setSize(400,120);
                
            }
        });

        recular.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                pclientes.setVisible(false);
                pproductos.setVisible(false);
                botoncitos.setVisible(true);
                proveer_nuevo.setVisible(false);

                adminJFrame.setSize(400,120);               
            }
        });
        //Metodo para cambiar al panel de boncitos
        volver3.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                pclientes.setVisible(false);
                pproductos.setVisible(false);
                pproveedores.setVisible(false);
                botoncitos.setVisible(true);
                adminJFrame.setSize(400,120);
                
            }
        });


         sumar_Proveedor.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){  
                if (NProveedor.getText().isBlank()||localizazio.getText().isBlank()){
                    
                }
                else if(NProveedor.getText().equals("Nombre")==true ||localizazio.getText().equals("Localidad") ==true){
                }
                else{conectar("insert into Proveedores (Nombre, Ciudad) Values('"+NProveedor.getText()+"','"+localizazio.getText()+"');");}    
            }
        });
       sumar_Prove.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
               //NProveedor.getText().isBlank() && NProveedor.getText().equals("Nombre del proveedor") || localizazio.getText().isBlank() &&  
                if (sumar_idprV.getText().isBlank() || sumar_idpro.getText().isBlank() || sumar_precio.getText().isBlank()){
                }
                else if(sumar_idprV.getText().equals("Id preveedor")==true||sumar_idpro.getText().equals("Id producto")==true||sumar_precio.getText().equals("Precio")==true){
                }
                else{conectar("insert into Proveer (Id_Producto, Id_Proveedor,Precio_compra) VALUES("+sumar_idpro.getText()+","+sumar_idprV.getText()+","+sumar_precio.getText()+");" );}    
            }
        });

          //Metodo para enviar los datos del formulario a la base de datos
          aceptar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                 if(texto_nombre.getText().isBlank() || texto_telefono.getText().isBlank() || texto_correo.getText().isBlank() || texto_destino.getText().isBlank()|| Global.condiciones_value == false){
                    System.out.println("error");
                    JDialog error = new JDialog();
                    JPanel perror = new JPanel();
                    JLabel mensaje = new JLabel("Rellena los campos:( Nombre,Telefono,Correo y Destino)");
                    error.setTitle("Rellena todos los campos");
                    error.setSize(400,100);
                    error.add(mensaje);
                    error.setVisible(true);
                    

                 }else{
                    texto_nombre.getText();
                    texto_telefono.getText();
                    Global.correo_value = texto_correo.getText();
                    texto_destino.getText();
                                    //Creando el xml
                xml.creadorxml(texto_nombre.getText(), texto_telefono.getText(), Global.correo_value, texto_destino.getText(), texto_Box1.getText(), texto_Box2.getText(), texto_Box3.getText(), texto_Box4.getText(), texto_Box5.getText());

        
                
                if(Global.box1_value == true){
                    try {
                        Integer.parseInt(texto_Box1.getText());
                        Global.cantidad_box1 = Integer.parseInt(texto_Box1.getText());

                    } catch (NumberFormatException o) {
                        
                    }
                   
                }
                if(Global.box2_value == true){
                    try {
                        Integer.parseInt(texto_Box2.getText());
                        Global.cantidad_box2 = Integer.parseInt(texto_Box2.getText());
                       
                    } catch (NumberFormatException o) {
                        
                    }
                }
                if(Global.box3_value == true){
                    try {
                        Integer.parseInt(texto_Box3.getText());
                        Global.cantidad_box3 = Integer.parseInt(texto_Box3.getText());
                        
                    } catch (NumberFormatException o) {
                        
                    }
                }
                if(Global.box4_value == true){
                    try {
                        Integer.parseInt(texto_Box4.getText());
                        Global.cantidad_box4 = Integer.parseInt(texto_Box4.getText());
                    } catch (NumberFormatException o) {
                        
                    }
                }
                if(Global.box5_value == true){
                    try {
                        Integer.parseInt(texto_Box5.getText());
                        Global.cantidad_box5 = Integer.parseInt(texto_Box5.getText());
                    } catch (NumberFormatException o) {
                        
                    }
                }

               
                //Inserta datos del formulario usario a las base de datos con el metodo conectar y getid
                conectar("Insert ignore into Clientes(Nombre,Telefono,Correo) Values('"  + texto_nombre.getText() + "', '"  + texto_telefono.getText() +"', '" + texto_correo.getText() + "');");
                Global.id = getid("select id_Cliente from Clientes Where Correo ='" +texto_correo.getText()+"';",1);
                System.out.println(Global.id);
                conectar("Insert into Pedidos(Id_Cliente,Destino,Fecha_ini,Fecha_entrega) Values('" + Global.id + "', '" + texto_destino.getText() + "', '" + Global.fechaActual  +"','" + Global.fechaentrega+ "');");
            
                Global.id_pedido = getid("select Id_Pedidos from Pedidos order by Id_Pedidos;",2);
                System.out.println(Global.id_pedido);
               
                if(Global.box1_value == true){
                    conectar("Insert into Tienen(Id_Pedido,Id_Producto,Cantidad) Values('" + Global.id_pedido + "', 1," + Global.cantidad_box1 + ")");
                }
                if(Global.box2_value == true){
                    conectar("Insert into Tienen(Id_Pedido,Id_Producto,Cantidad) Values('" + Global.id_pedido + "', 2," + Global.cantidad_box2 + ")");
                }
                if(Global.box3_value == true){
                    conectar("Insert into Tienen(Id_Pedido,Id_Producto,Cantidad) Values('" + Global.id_pedido + "', 3," + Global.cantidad_box3 + ")");
                }
                if(Global.box4_value == true){
                    conectar("Insert into Tienen(Id_Pedido,Id_Producto,Cantidad) Values('" + Global.id_pedido + "', 4," + Global.cantidad_box4 + ")");
                }
                if(Global.box5_value == true){
                    conectar("Insert into Tienen(Id_Pedido,Id_Producto,Cantidad) Values('" + Global.id_pedido + "', 5," + Global.cantidad_box5 + ")");
                }
            
            }
            }
        });
        
        //Metodo para comprobar el estado de las checkbox
        Box1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e){
               if(e.getStateChange() == ItemEvent.SELECTED){
                Global.box1_value = true;
                }else{
                Global.box1_value = false;
            }
            }
        });


            //Metodo para comprobar el estado de las checkbox
            Box2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e){
               if(e.getStateChange() == ItemEvent.SELECTED){
                Global.box2_value = true;
                }else{
                Global.box2_value = false;
            }
            }
        });
        
        //Metodo para comprobar el estado de las checkbox
        Box3.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e){
               if(e.getStateChange() == ItemEvent.SELECTED){
                Global.box3_value = true;
                }else{
                Global.box3_value = false;
            }
            }
        });
        
        //Metodo para comprobar el estado de las checkbox
        Box4.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e){
               if(e.getStateChange() == ItemEvent.SELECTED){
                Global.box4_value = true;
                }else{
                Global.box4_value = false;
            }
            }
        });
        
        //Metodo para comprobar el estado de las checkbox
        Box5.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e){
               if(e.getStateChange() == ItemEvent.SELECTED){
                Global.box5_value = true;
                }else{
                Global.box5_value = false;
            }
            }
        });
       

         //Metodo para comprobar el estado de las checkbox
        condiciones.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e){
               if(e.getStateChange() == ItemEvent.SELECTED){
                Global.condiciones_value = true;
                }else{
                Global.condiciones_value = false;
            }
            }
        });



    });
   
}

    







  //Crear conexion entre el codigo java y base de datos para insertar datos
  public static void conectar(String query){ 
    try {
       Connection connection = DriverManager.getConnection(
                "jdbc:mysql://mysql-8001.dinaserver.com:3306/RapiTrack?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC", "jon", "Panafr@n2"
        );
        Statement statement = connection.createStatement();
        int filas = statement.executeUpdate(query);
   connection.close();} 
   catch (Exception e) {
   System.out.println(e);
   }
    }



      //Crear conexion entre el codigo java y base de datos para hacer consultas
  public static int getid(String query, int queid){
    try {
       Connection connection = DriverManager.getConnection(
                "jdbc:mysql://mysql-8001.dinaserver.com:3306/RapiTrack?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC", "jon", "Panafr@n2"
        );
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = statement.executeQuery(query);
       int id=0;
        if(queid == 1){
            rs.next();
             id = rs.getInt(1);
            connection.close();}
       
         if(queid == 2){ 
              while (rs.next()){
                rs.last();
               id = rs.getInt(1);
                
                }
                connection.close();
            }

       
        return id;
   } 
   catch (Exception e) {
   System.out.println(e);
   return 0;
   }
    }
//Saca las tablas de base de datos y las pasa a una JTable para ser mostradas
    public static  void ADcliente(String query, DefaultTableModel modelo){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://mysql-8001.dinaserver.com:3306/RapiTrack?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC", "jon", "Panafr@n2"
            );
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            ResultSetMetaData metaData = rs.getMetaData();
            int columnas = metaData.getColumnCount();

            // Agregar los nombres de las columnas al modelo
            modelo.setRowCount(0);
            modelo.setColumnCount(0);
            for (int i = 1; i <= columnas; i++) {
                modelo.addColumn(metaData.getColumnName(i));
            }

        // Agregar las filas al modelo
        while (rs.next()) {
            Object[] fila = new Object[columnas];
            for (int i = 0; i < columnas; i++) {
                fila[i] = rs.getObject(i + 1);
            }
            modelo.addRow(fila);
        }
        connection.close();
    } catch (Exception e) {
        System.out.println(e);
    }
}

//Crear conexion entre el codigo java y base de datos para update de datos
    public static void updates(String query){
        try {
            Connection connection = DriverManager.getConnection(
              "jdbc:mysql://mysql-8001.dinaserver.com:3306/RapiTrack?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC", "jon", "Panafr@n2"
        );
            Statement statement = connection.createStatement();
            int filas = statement.executeUpdate(query);
            connection.close();} 
        catch (Exception e) {
        System.out.println(e);
         }
        }
   
    
}
