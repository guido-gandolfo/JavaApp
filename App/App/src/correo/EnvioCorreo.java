package correo;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.sql.DataSource;
import javax.activation.*;

public class EnvioCorreo {

  private static Multipart multipart;

public void enviar () {

    // La dirección de envío (to)
    String para = "mail@gmail.com";

    // La dirección de la cuenta de envío (from)
    String de = "appmail@gmail.com";

    // El servidor (host). En este caso usamos localhost
    String host = "localhost";

    // Obtenemos las propiedades del sistema
    Properties propiedades = System.getProperties();

    // Configuramos el servidor de correo
    propiedades.setProperty("mail.smtp.host", host);
    propiedades.setProperty("mail.user", "appmail@gmail.com"); propiedades.setProperty("mail.password", "1234");
    // Obtenemos la sesión por defecto
    Session sesion = Session.getDefaultInstance(propiedades);

    try{
      // Creamos un objeto mensaje tipo MimeMessage por defecto.
      MimeMessage mensaje = new MimeMessage(sesion);

      // Asignamos el “de o from” al header del correo.
      mensaje.setFrom(new InternetAddress(de));

      // Asignamos el “para o to” al header del correo.
      mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(para));

      // Asignamos el asunto
      mensaje.setSubject("Primer correo con archivos adjuntos");

      // Creamos un cuerpo del correo con ayuda de la clase BodyPart
      BodyPart cuerpoMensaje = new MimeBodyPart();

      // Asignamos el texto del correo
      cuerpoMensaje.setText("Este el texto del correo");

      // Creamos un multipart al correo
      Multipart multiparte = new MimeMultipart();

      // Agregamos el texto al cuerpo del correo multiparte
      multiparte.addBodyPart(cuerpoMensaje);

      // Ahora el proceso para adjuntar el archivo
      cuerpoMensaje = new MimeBodyPart();
      String nombreArchivo = "consulta.pdf";
      DataSource fuente = new FileDataSource(nombreArchivo);
      cuerpoMensaje.setDataHandler(new DataHandler(fuente));
      cuerpoMensaje.setFileName(nombreArchivo);
      multipart.addBodyPart(cuerpoMensaje);

      // Asignamos al mensaje todas las partes que creamos anteriormente
      mensaje.setContent(multiparte);

      // Enviamos el correo
      Transport.send(mensaje);
      System.out.println("Mensaje enviado");
    } catch (MessagingException e) {
      e.printStackTrace();
    }
  }
}