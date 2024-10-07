
package proyecto_func.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface FirebaseStorageService {
    
    public String cargaImagen(MultipartFile archivoLocalCliente, String carpeta, Long id);

    //El BuketName es el <id_del_proyecto> + ".appspot.com"
    final String bucketName = "proyecto-grupo2-27cc3.appspot.com";

    //Esta es la ruta básica de este proyecto Techshop
    final String rutaSuperiorStorage = "FUNC";

    //Ubicación donde se encuentra el archivo de configuración Json
    final String rutaJsonFile = "firebase"; //Que es el folder que se encuentra src/main/resources 
    
    //El nombre del archivo Json
    final String archivoJsonFile = "proyecto-grupo2-27cc3-firebase-adminsdk-7t0u3-022af4f3db.json"; //Importante ponerle la extension "json"
}
