package es.florida.AE04_GUI;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;

public class Vista {

	private JFrame frmBiblioteca;
	private JButton btnImport;
	private JButton btnConsulta1;
	private JButton btnConsulta2;
	private JButton btnConsulta;
	private JTextField textField;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	private JButton btnBorrar;
	private JScrollPane scrollPane_1;
	private JTextArea textArea_1;
	private JLabel lblResultadoDeLa;

	public Vista() {
		initialize();
	}

	private void initialize() {
		frmBiblioteca = new JFrame();
		frmBiblioteca.setTitle("BIBLIOTECA");
		frmBiblioteca.setBounds(100, 100, 682, 557);
		frmBiblioteca.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBiblioteca.getContentPane().setLayout(null);

		btnImport = new JButton("Importar");
		btnImport.setBounds(10, 391, 89, 23);
		frmBiblioteca.getContentPane().add(btnImport);

		btnConsulta1 = new JButton("Consulta 1");
		btnConsulta1.setBounds(416, 391, 102, 23);
		frmBiblioteca.getContentPane().add(btnConsulta1);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 71, 642, 309);
		frmBiblioteca.getContentPane().add(scrollPane);

		btnConsulta2 = new JButton("Consulta 2");
		btnConsulta2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnConsulta2.setBounds(544, 391, 102, 23);
		frmBiblioteca.getContentPane().add(btnConsulta2);

		btnConsulta = new JButton("Consulta");
		btnConsulta.setBounds(10, 484, 89, 23);
		frmBiblioteca.getContentPane().add(btnConsulta);

		textField = new JTextField();
		textField.setBounds(10, 450, 642, 23);
		frmBiblioteca.getContentPane().add(textField);
		textField.setColumns(10);

		btnBorrar = new JButton("Limpiar");
		btnBorrar.setBounds(563, 484, 89, 23);
		frmBiblioteca.getContentPane().add(btnBorrar);

		JLabel lblNewLabel = new JLabel("Inserte una consulta personalizada");
		lblNewLabel.setForeground(Color.GRAY);
		lblNewLabel.setBounds(10, 435, 267, 14);
		frmBiblioteca.getContentPane().add(lblNewLabel);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 25, 642, 20);
		frmBiblioteca.getContentPane().add(scrollPane_1);

		textArea_1 = new JTextArea();
		scrollPane_1.setViewportView(textArea_1);

		JLabel lblOrdenDeLos = new JLabel("Orden de los datos");
		lblOrdenDeLos.setForeground(Color.GRAY);
		lblOrdenDeLos.setBounds(10, 11, 267, 14);
		frmBiblioteca.getContentPane().add(lblOrdenDeLos);

		lblResultadoDeLa = new JLabel("Resultado de la consulta");
		lblResultadoDeLa.setForeground(Color.GRAY);
		lblResultadoDeLa.setBounds(10, 56, 267, 14);
		frmBiblioteca.getContentPane().add(lblResultadoDeLa);

		this.frmBiblioteca.setVisible(true);
	}

	// GETTERS Botones
	public JTextArea getTextArea() {
		return textArea;
	}

	public JTextArea getTextArea1() {
		return textArea_1;
	}

	public JButton getBtnImport() {
		return btnImport;
	}

	public JButton getBtnConsulta1() {
		return btnConsulta1;
	}

	public JButton getBtnConsulta2() {
		return btnConsulta2;
	}

	public JButton getBtnConsulta() {
		return btnConsulta;
	}

	public JButton getBtnBorrar() {
		return btnBorrar;
	}

	public JTextField getTextField() {
		return textField;
	}
}
