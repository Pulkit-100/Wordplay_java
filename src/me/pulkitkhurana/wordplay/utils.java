package me.pulkitkhurana.wordplay;


import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

class AppendingObjectOutputStream extends ObjectOutputStream {

    public AppendingObjectOutputStream(OutputStream out) throws IOException {
        super(out);
    }

    @Override
    protected void writeStreamHeader() throws IOException {
        // do not write a header, but reset:
        // this line added after another question
        // showed a problem with the original
        reset();
    }
}

public class utils {
    public static void introScreen(){
            System.out.print("\n\n\n\n");
            System.out.print("                                                                                        dddddddd\n" );
            System.out.print("                                                                                        d::::::d                   lllllll\n" );
            System.out.print("                                                                                        d::::::d                   l:::::l\n" );
            System.out.print("                                                                                        d:::::d                    l:::::l\n" );
            System.out.print("wwwwwww           wwwww           wwwwwww ooooooooooo   rrrrr   rrrrrrrrr       ddddddddd:::::dppppp   ppppppppp    l::::l                yyyyyyy          yyyyyyy\n" );
            System.out.print("w:::::w         w:::::w         w:::::woo:::::::::::oo r::::rrr:::::::::r    dd::::::::::::::dp::::ppp:::::::::p    l::::l            a::::ay:::::y       y:::::y\n" );
            System.out.print("   w:::::w     w:::::::::w     w:::::w o:::::ooooo:::::orr::::::rrrrr::::::rd:::::::ddddd:::::dpp::::::ppppp::::::p l::::l            a::::a y:::::y     y:::::y\n" );
            System.out.print("    w:::::w   w:::::w:::::w   w:::::w  o::::o     o::::o r:::::r     r:::::rd::::::d    d:::::d p:::::p     p:::::p l::::l     aaaaaaa:::::a  y:::::y   y:::::y\n" );
            System.out.print("     w:::::w w:::::w w:::::w w:::::w   o::::o     o::::o r:::::r     rrrrrrrd:::::d     d:::::d p:::::p     p:::::p l::::l   aa::::::::::::a   y:::::y y:::::y\n" );
            System.out.print("      w:::::w:::::w   w:::::w:::::w    o::::o     o::::o r:::::r            d:::::d     d:::::d p:::::p     p:::::p l::::l  a::::aaaa::::::a    y:::::y:::::y\n" );
            System.out.print("       w:::::::::w     w:::::::::w     o::::o     o::::o r:::::r            d:::::d     d:::::d p:::::p    p::::::p l::::l a::::a    a:::::a     y:::::::::y\n" );
            System.out.print("        w:::::::w       w:::::::w      o:::::ooooo:::::o r:::::r            d::::::ddddd::::::ddp:::::ppppp:::::::pl::::::la::::a    a:::::a      y:::::::y\n" );
            System.out.print("         w:::::w         w:::::w       o:::::::::::::::o r:::::r             d:::::::::::::::::dp::::::::::::::::p l::::::la:::::aaaa::::::a       y:::::y\n" );
            System.out.print("          w:::w           w:::w         oo:::::::::::oo  r:::::r              d:::::::::ddd::::dp::::::::::::::pp  l::::::l a::::::::::aa:::a     y:::::y\n" );
            System.out.print("           www             www            ooooooooooo    rrrrrrr               ddddddddd   dddddp::::::pppppppp    llllllll  aaaaaaaaaa  aaaa    y:::::y\n" );
            System.out.print("                                                                                                p:::::p                                         y:::::y\n" );
            System.out.print("                                                                                                p:::::p                                        y:::::y\n" );
            System.out.print("                                                                                               ppppppppp \n" );

        
    }
}
