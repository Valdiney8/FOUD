package ufv.com.br.perambiental;

import java.io.File;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import ufv.com.br.foud.EstrategiaTransmissao;

public class Http implements EstrategiaTransmissao {
    //private String destino = "http://10.0.1.2:1081/testing/save_file.php";
    private String destino = "http://192.168.43.33:1081/testing/save_file.php";
    //private String destino = "http://200.131.245.100:1081/testing/save_file.php";


    public boolean transmitir(File arquivo){
        if (arquivo.exists()) {
            String content_type = "apllication/octet-stream";
            String file_path = arquivo.getAbsolutePath();
            OkHttpClient client = new OkHttpClient();
            RequestBody file_body = RequestBody.create(MediaType.parse(content_type), arquivo);

            RequestBody request_body = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("type", content_type)
                    .addFormDataPart("uploaded_file", file_path.substring(file_path.lastIndexOf("/") + 1), file_body)
                    .build();

            Request request = new Request.Builder()
                    .url(destino)
                    .post(request_body)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                if(!response.isSuccessful()){
                    return false;
                    //throw new IOException("Error : "+response);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
