import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.FileDialog;

import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
 
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
 
/**
 *
 * @author ELIZABETH
 */
public class Formulario extends JFrame implements ActionListener
{
	private JLabel jlTexto;
	private JTextArea jtaTexto;
	private JButton jbSalir, jbGuardar;
	private JScrollPane jspTexto;
	private Archivo archivo;
	private Font fuente;
 
	public Formulario()
	{
		super("Guardar texto.");
		setSize(500,500);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		
		fuente=new Font("Tahoma", Font.BOLD, 16);
		jlTexto=new JLabel("Escriba el texto");
		jlTexto.setBounds(1,1,150,25);
		getContentPane().add(jlTexto);
		
		jtaTexto=new JTextArea();
		jtaTexto.setFont(fuente);
		
		jtaTexto.setBounds(1,35,490,370);
		jspTexto=new JScrollPane(jtaTexto);
		jspTexto.setBounds(1,35,490,370);
		getContentPane().add(jspTexto);
		jbGuardar=new JButton("Guardar");
		
		jbGuardar.setBounds(10,425,100,25);
		getContentPane().add(jbGuardar);
		jbGuardar.addActionListener(this);
		jbSalir=new JButton("Salir");
		jbSalir.setBounds(385,425,100,25);
		jbSalir.addActionListener(this);
		getContentPane().add(jbSalir);
		archivo=new Archivo();
	}
 
	public static void main(String args[])
	{
		Formulario frm=new Formulario();
		frm.show();
	}
 
	public void actionPerformed(ActionEvent evt)
	{
		click_guardar(evt);
		click_salir(evt);
	}
 
	public void click_guardar(ActionEvent evt)
	{
		if(evt.getActionCommand().equalsIgnoreCase("Guardar"))
		{
			JOptionPane.showMessageDialog(null, archivo.escribir_archivo(jtaTexto.getText()) );
		}
	}
 
	public void click_salir(ActionEvent evt)
	{
		if(evt.getActionCommand().equalsIgnoreCase("Salir"))
		{
			System.exit(0);
		}
	}
 

class Archivo
{
	private File flArchivo;
	private String Ruta_del_archivo;
	private BufferedWriter bwEscritor;
	private FileWriter fwArchivo_en_el_que_escribir;
	private FileDialog fdGuardar;
 
	public Archivo()
	{
		fdGuardar=new FileDialog(fdGuardar, "Guardar como", FileDialog.SAVE);
 
		Ruta_del_archivo="";
		flArchivo=new File(Ruta_del_archivo);
	}
 
	public String escribir_archivo(String texto_a_guardar)
	{
		String mTextos[]=texto_a_guardar.split("\n"), respuesta="";
		fdGuardar.setVisible(true);
		fdGuardar.dispose();
		Ruta_del_archivo=fdGuardar.getDirectory()+fdGuardar.getFile() + ".txt";
		flArchivo=new File(Ruta_del_archivo);
		
		if(flArchivo.exists())
		{
			respuesta="El archivo ya existia y ha sido sobreescrito. \nEn la direccion: " +  flArchivo.getPath( ) ;
		}
		else
		{
			respuesta="El archivo ha sido creado exitosamente.\nEn la direccion: " +  flArchivo.getPath( ) ;
		}
 
		try{
			
			fwArchivo_en_el_que_escribir=new FileWriter(flArchivo);
			for (int i=0; i<=mTextos.length-1;i++)
			{
				
				fwArchivo_en_el_que_escribir.write(mTextos[i] + "\r\n" );
			}
			
			bwEscritor=new BufferedWriter(fwArchivo_en_el_que_escribir);
			bwEscritor.close();//Se cierra el archivo.		
		}catch(Exception ex){JOptionPane.showMessageDialog(null,ex.getMessage());}
 
		return respuesta;
	}
 
}
}
