import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;

public class Input extends JFrame {

	private JPanel contentPane;
	private JSpinner spinnerNum;
	private JSpinner[] txtArrays;
	private JLabel[] lbArrays;
	private int num = 0;
	private int[] arrays;
	private JButton btnOK;
	/*
	 * Chạy ứng dụng.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				setLockAndFeel();
				try {
					Input frame = new Input();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/*
	 * Tạo frame.
	 */
	public Input() {
		setTitle("Nh\u1EADp d\u1EEF li\u1EC7u m\u1EA3ng");
		setBounds(100, 100, 504, 312);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("(Số phần tử của mảng từ 2 đến 15)");
		lblNewLabel.setBounds(10, 8, 200, 25);
		contentPane.add(lblNewLabel);
		
		SpinnerModel sm = new SpinnerNumberModel(2, 2, 15, 1);
		spinnerNum = new JSpinner(sm);
		spinnerNum.setBounds(217, 8, 100, 25);
		contentPane.add(spinnerNum);	
		
		JButton btnCreateArray = new JButton("Tạo mảng");
		btnCreateArray.setBackground(SystemColor.activeCaption);
		btnCreateArray.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createArray();
			}
		});
		btnCreateArray.setBounds(337, 8, 120, 25);
		contentPane.add(btnCreateArray);
		
		btnOK = new JButton("Xác Nhận");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				beAccepted();
				JOptionPane.showMessageDialog(contentPane, "Tạo mảng thành công");
			}
		});
		btnOK.setBackground(SystemColor.activeCaption);
		btnOK.setBounds(185, 237, 120, 25);
		contentPane.add(btnOK);
		
	}
	
	public static void setLockAndFeel() {
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    System.out.println("Cannot set Lock and Feel! Errors!");
		}
	}
	
	public void createArray() {
		deleteArrays();
		num = (Integer)spinnerNum.getValue();
		arrays = new int[num];
		lbArrays = new JLabel[num];
		txtArrays = new JSpinner[num];
		arrays = new int[num];
		
		for (int i = 0; i < num; i++) {
			lbArrays[i] = new JLabel("A[" + i + "]:");
			SpinnerModel smValue = new SpinnerNumberModel();
			txtArrays[i] = new JSpinner(smValue);
			contentPane.add(lbArrays[i]);
			contentPane.add(txtArrays[i]);
			lbArrays[i].setSize(60,30);
			if (i == 0 || i == 5 || i == 10) 		//đủ 5 dòng thì chuyển hàng.
				lbArrays[i].setLocation(150 * (i + 1)/5  , 40);
			else
				lbArrays[i].setLocation(lbArrays[i-1].getX(), lbArrays[i-1].getY() + 40);
			txtArrays[i].setSize(50,30);
			txtArrays[i].setLocation(lbArrays[i].getX() + 40, lbArrays[i].getY());
		}
		contentPane.setVisible(true);
		contentPane.validate();
		contentPane.repaint();
	}
	
	public void deleteArrays() {
		for (int i = 0; i < num; i++) {
			lbArrays[i].setVisible(false);
			txtArrays[i].setVisible(false);
			contentPane.remove(lbArrays[i]);
			contentPane.remove(txtArrays[i]);
		}
	}
	
	public void beAccepted() {
		for (int i = 0; i < num; i++) {
			arrays[i] = (int) txtArrays[i].getValue();
			System.out.println(arrays[i]);	
		}
		Frame[] listFrames = Frame.getFrames();
		((AlgorithmSimulation) listFrames[0]).setArray(arrays);
	}
}
