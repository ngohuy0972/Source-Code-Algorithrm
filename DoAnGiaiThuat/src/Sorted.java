import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Sorted extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/*
	 * Chạy ứng dụng.
	 */
	public static void main(String[] args) {
		try {
			Sorted dialog = new Sorted();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Tạo hộp thoại.
	 */
	public Sorted() {
//		setBounds(100, 100, 200, 100);
//		getContentPane().setLayout(new BorderLayout());
//		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
//		getContentPane().add(contentPanel, BorderLayout.CENTER);
//		contentPanel.setLayout(new BorderLayout(0, 0));
//		
//		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
//		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
//		
		{
			JLabel lbComplete = new JLabel();
			JOptionPane.showMessageDialog(lbComplete, "Đã sắp xếp xong", "Thông Báo", 1);
			lbComplete.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lbComplete);
		}
	}

}
