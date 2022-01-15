package com.example.jumpers;

import java.io.IOException;
import java.io.OutputStream;

public class Test {
    OutputStream os = null;
    public static void main(String[] args) throws IOException {
      //  contains(null,null); //null
        contains("",null); //ok

        //   contains("Sollers", "Sollers Consulting"); //StringIndexOutOfBoundsException

        contains("","Sollers" );  //brak błędu
    }

    public static boolean contains(String message, String phrase){
        boolean matches = false;
        for(int m =0 ; m< message.length() ; m++){
            matches =true;
            for (int p=0; p<phrase.length() && matches;p++){
                if (message.charAt(m+p) != phrase.charAt(p)){
                    matches =false;
                    break;
                }
                else{
                    matches=true;
                }
            }
            if(matches){
                return true;
            }
        }
        return matches;
    }

    //OutputStream os = null;
        /* System.out.println(os);
        try {
            os = new OutputStream() {
                @Override
                public void write(int b) throws IOException {

                }
            };
            os.close();
            os.close();
            }catch (Exception e){

            if(os!=null){
                os.close();
            }

        }
        finally {
            System.out.println(os);
            if(os!=null){
                System.out.println(os);
                os.close();
            }
        }
        os.close();
        System.out.println(os);
*/

}
