import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class About extends JFrame {

	private JPanel contentPane;

	/*
	 * Chạy ứng dụng.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					About frame = new About();
					frame.setVisible(true);
			        frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/*
	 * Tạo khung thông tin.
	 */
	public About() {
		setBounds(100, 100, 400, 200);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new TitledBorder(null, "Th\u00F4ng tin", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		JTextArea txtInfo = new JTextArea();
		txtInfo.setEditable(false);
		txtInfo.setFont(new Font("Arial", Font.PLAIN, 16));
		txtInfo.setBackground(SystemColor.menu);
		txtInfo.setText(" Đồ Án Phân Tích Và Thiết Kế Giải Thuật. \n Sinh Viên 1: Ngô Văn Huy \n Sinh Viên 2: Trần Ngọc Mậu \n Giáo viên hướng dẫn: Đỗ Phúc Hảo \n SĐT : 0856787657 \n Email: huyngoit99@gmail.com");
		contentPane.add(txtInfo);
	}
}
