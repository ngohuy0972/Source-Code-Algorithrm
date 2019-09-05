import java.awt.Color;
import java.awt.Dialog.ModalExclusionType;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.NumberFormatter;

import com.sun.javafx.runtime.SystemProperties;

import javax.swing.border.EtchedBorder;
import javax.swing.JScrollBar;
import java.awt.ScrollPane;

public class AlgorithmSimulation extends JFrame {

	private static final String QuickSort = null;
	/*
	 * Khai báo các đối tượng có trên Frame.
	 */
	
	private JPanel contentPane;
	private JLabel lbTitle;
	private JPanel pnArrayArea;
	private JPanel pnTool;
	private JPanel pnArray;
	private JPanel pnCreateArray;
	private JLabel lbNum, lbMaxNum;
	private JSpinner spNum;
	private JButton btnCreateArray, btnDeleteArray, btnSetZero;
	private JButton btnRandom, btnByHand;
	private JPanel pnCode;
	private JSlider slSize;
	private JScrollPane pnScroll; 
	private DefaultListModel<String> model;
	private ActionListener eBubbleSort,eQuickSort, eMergeSort;
	private ChangeListener eSize;
	private JList<String> lsCode;
	private JPanel pnAlgorithm;
	private JRadioButton rdBubbleSort, rdQuickSort, rdMergeSort;
	private ButtonGroup grSort;
	private JPanel pnControl;
	private JRadioButton rdIncrease, rdDecrease;
	private ActionListener eIncrease, eDecrease;
	private boolean isIncrease = true;
	private JButton btnSort, btnStop;
	private JSlider slSpeed;
    private ChangeListener eSpeed;
	public int num;
	private JLabel[] lbArrays;
	private int[] array;
	private static AlgorithmSimulation frame;
	private Thread[] threads = new Thread[1000000];
	private int curT = -1;
	private int time = 85;
    private int step = 0;	
    private int[] lbI = new int[50];
    private int[] lbJ = new int[50];
    private int[] oriLocat = new int[15];
    private int[] lbL = new int[50];
    private int[] lbR = new int[50];
    private JButton btnAboutButton;
    private JLabel lbPoint1 = new JLabel();
    private JLabel lbPoint2 = new JLabel();
    private JLabel lbPointM = new JLabel();
    private Color processingColor = new Color(255, 153, 153);
    private Color selectedGreen = new Color(153, 255, 153);
    private Color selectedYellow = new Color(255, 255, 153);
    private boolean flagStep = false;	
    private boolean flagContinue = true;	//dừng lại hoặc tiếp tục.
    
    /* Action Pause */
    private void actionPause() {
	if(flagStep) {
	    flagStep = false;
	    flagContinue = true;
	    btnStop.setText("Tạm dừng");
	    btnStop.setToolTipText("Tạm dừng chương trình");
	} else
	    if(flagContinue) {
	    	btnStop.setText("Tiếp tục");
	    	btnStop.setToolTipText("Tiếp tục chương trình");
	    	flagContinue = false;
	    } else {
	    	flagContinue = true;
	    	btnStop.setText("Tạm dừng");
	    	btnStop.setToolTipText("Tạm dừng chương trình");
	    }
		stopAllThreads();
    }
    /*
     * Dừng các Threads.
     */  
        public void stopAllThreads() {
        	for(int i = 0; i < curT; i++) {
        		try {
					threads[i].join(time);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        } 
    }
    
	/*
	 * Chạy ứng dụng.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				setUI();
				try {
					frame = new AlgorithmSimulation();
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH); //set JFrame full hiển thị
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/*
	 * Set vẻ ngoài của giao diện.
	 */
	public static void setUI() {
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    
		}
	}

	/*
	 * Tạo giao diện trên Frame.
	 */
	public AlgorithmSimulation() {
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setTitle("M\u00F4 ph\u1ECFng thu\u1EADt to\u00E1n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1376, 742);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lbTitle = new JLabel("M\u00D4 PH\u1ECENG THU\u1EACT TO\u00C1N");
		lbTitle.setBackground(SystemColor.menu);
		lbTitle.setBounds(5, 5, 1286, 28);
		lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitle.setFont(new Font("Tahoma", Font.BOLD, 23));
		contentPane.add(lbTitle);
		
		pnTool = new JPanel();
		pnTool.setBackground(SystemColor.control);
		pnTool.setBounds(5, 415, 1350, 287);
		contentPane.add(pnTool);
		
		pnArray = new JPanel();
		pnArray.setBackground(SystemColor.menu);
		pnArray.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "D\u1EEF li\u1EC7u", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		pnCode = new JPanel();
		pnCode.setBackground(SystemColor.menu);
		pnCode.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Code Thu\u1EADt To\u00E1n", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		pnAlgorithm = new JPanel();
		pnAlgorithm.setBackground(SystemColor.menu);
		pnAlgorithm.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "L\u1EF1a ch\u1ECDn thu\u1EADt to\u00E1n", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		pnControl = new JPanel();
		pnControl.setBackground(SystemColor.menu);
		pnControl.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "\u0110i\u1EC1u khi\u1EC3n", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GroupLayout gl_pnTool = new GroupLayout(pnTool);
		
		//set nhóm vị trí và kích thước các thành phần dọc theo trục ngang.
		gl_pnTool.setHorizontalGroup(
			gl_pnTool.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnTool.createSequentialGroup()
					.addComponent(pnArray, GroupLayout.PREFERRED_SIZE, 319, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(pnCode, GroupLayout.PREFERRED_SIZE, 506, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(pnAlgorithm, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(pnControl, GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE))
		);
		
		//set nhóm vị trí và kích thước các thành phần dọc theo trục dọc.
		gl_pnTool.setVerticalGroup(
			gl_pnTool.createParallelGroup(Alignment.TRAILING)
				.addComponent(pnArray, GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
				.addComponent(pnCode, GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
				.addComponent(pnControl, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
				.addGroup(gl_pnTool.createSequentialGroup()
					.addContainerGap(94, Short.MAX_VALUE)
					.addComponent(pnAlgorithm, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE))
		);
		pnControl.setLayout(null);
		
		slSpeed = new JSlider();
		slSpeed.setOrientation(SwingConstants.VERTICAL);
		slSpeed.setBounds(237, 76, 32, 179);
        slSpeed.setMinimum(1);
        slSpeed.setMaximum(9);
        slSpeed.setValue(5);
		pnControl.add(slSpeed);
		
		rdIncrease = new JRadioButton("S\u1EAFp x\u1EBFp t\u0103ng d\u1EA7n");
		rdIncrease.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdIncrease.setBounds(52, 71, 159, 23);
		pnControl.add(rdIncrease);
		
		rdDecrease = new JRadioButton("S\u1EAFp x\u1EBFm gi\u1EA3m d\u1EA7n");
		rdDecrease.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdDecrease.setBounds(52, 115, 159, 23);
		pnControl.add(rdDecrease);
		
		btnSort = new JButton("S\u1EAFp x\u1EBFp");
		btnSort.setBackground(SystemColor.activeCaption);
		btnSort.setToolTipText("Sắp xếp mảng");
		btnSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				if (!isSorted()) {
					setState(3);
					for (int i = 0; i < num; i++) 
						lbArrays[i].setForeground(Color.BLACK);

					if (rdBubbleSort.isSelected()) {
						BubbleSort();
						//threadReLocate();
					}
					
					if (rdQuickSort.isSelected()) {
						QuickSort();
						//threadReLocate();
					}
					if (rdMergeSort.isSelected()) {
						MergeSort();
						//threadReLocate();
					}
					waitSorted();						
				}
			}
		});
		btnSort.setBounds(52, 167, 120, 25);
		pnControl.add(btnSort);
		
		btnStop = new JButton("Dừng lại");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPause();
				setState(0);
			}
		});
		btnStop.setBackground(SystemColor.activeCaption);
		btnStop.setBounds(52, 215, 120, 25);
		pnControl.add(btnStop);
		pnAlgorithm.setLayout(null);
		
		rdBubbleSort = new JRadioButton("Bubble Sort");
		rdBubbleSort.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdBubbleSort.setBounds(35, 65, 127, 23);
		pnAlgorithm.add(rdBubbleSort);
		
		rdQuickSort = new JRadioButton("Quick Sort");
		rdQuickSort.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdQuickSort.setBounds(35, 27, 127, 23);
		pnAlgorithm.add(rdQuickSort);
		
		rdMergeSort = new JRadioButton("Merge Sort");
		rdMergeSort.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdMergeSort.setBounds(35, 101, 127, 23);
		pnAlgorithm.add(rdMergeSort);
		pnCode.setLayout(null);
		
		slSize = new JSlider();		//thanh thu phòng cỡ chữ ở lscode.
                slSize.setMinimum(10);
                slSize.setMaximum(20);
                slSize.setValue(14);
		slSize.setBounds(20, 21, 466, 26); 
		pnCode.add(slSize);
		
		pnScroll = new JScrollPane();		//Thanh cuộn code mô phỏng.
		pnScroll.setBounds(15, 58, 476, 218); 
		pnScroll.getVerticalScrollBar().setUnitIncrement(14);
		pnCode.add(pnScroll);
		
		model = new DefaultListModel<>();
		lsCode = new JList<String>(model);
		lsCode.setBorder(new LineBorder(new Color(0, 0, 0)));
		lsCode.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lsCode.setFont(new Font("Monospaced",Font.BOLD,14));
		pnScroll.setViewportView(lsCode);		//hiển thị code mô phỏng lên khung paneScroll
		
		pnCreateArray = new JPanel();
		pnCreateArray.setBackground(SystemColor.menu);
		pnCreateArray.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Kh\u1EDFi t\u1EA1o m\u1EA3ng", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GroupLayout gl_pnArray = new GroupLayout(pnArray);
		gl_pnArray.setHorizontalGroup(
			gl_pnArray.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnArray.createSequentialGroup()
					.addContainerGap()
					.addComponent(pnCreateArray, GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_pnArray.setVerticalGroup(
			gl_pnArray.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnArray.createSequentialGroup()
					.addContainerGap()
					.addComponent(pnCreateArray, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		lbNum = new JLabel("S\u1ED1 ph\u1EA7n t\u1EED m\u1EA3ng\r\n:");
		lbNum.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbNum.setBounds(80, 21, 125, 25);
		
		SpinnerModel sm = new SpinnerNumberModel(2, 2, 15, 1); 		//set số phần tử max và min.
		spNum = new JSpinner(sm);
		spNum.setFont(new Font("Tahoma", Font.PLAIN, 15));
		spNum.setBounds(80, 69, 130, 25);
		JFormattedTextField txt = ((JSpinner.NumberEditor) spNum.getEditor()).getTextField();
		((NumberFormatter) txt.getFormatter()).setAllowsInvalid(false);
		
		btnCreateArray = new JButton("T\u1EA1o m\u1EA3ng");
		btnCreateArray.setBackground(SystemColor.activeCaption);
		btnCreateArray.setToolTipText("Tạo Mảng");
		btnCreateArray.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createArrays();
			}
		});
		btnCreateArray.setBounds(91, 105, 109, 25);
		
		btnDeleteArray = new JButton("X\u00F3a m\u1EA3ng");
		btnDeleteArray.setBackground(SystemColor.activeCaption);
		btnDeleteArray.setToolTipText("Xóa Mảng");
		btnDeleteArray.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deleteArrays();		
				setState(0);
			}
		});
		btnDeleteArray.setBounds(160, 186, 109, 25);
		
		btnSetZero = new JButton("\u0110\u1EB7t v\u1EC1 0");
		btnSetZero.setBackground(SystemColor.activeCaption);
		btnSetZero.setToolTipText("Reset các giá trị về 0");
		btnSetZero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setZero();
			}
		});
		btnSetZero.setBounds(16, 186, 109, 25);
		pnCreateArray.setLayout(null);
		pnCreateArray.add(lbNum);
		pnCreateArray.add(btnSetZero);
		pnCreateArray.add(btnCreateArray);
		pnCreateArray.add(spNum);
		pnCreateArray.add(btnDeleteArray);
		
		lbMaxNum = new JLabel("(T\u1ED1i \u0111a 15)");
		lbMaxNum.setHorizontalAlignment(SwingConstants.CENTER);
		lbMaxNum.setBounds(83, 45, 109, 14);
		pnCreateArray.add(lbMaxNum);
		
		btnRandom = new JButton("Ng\u1EABu nhi\u00EAn");
		btnRandom.setBounds(16, 140, 109, 25);
		btnRandom.setToolTipText("Tạo mảng ngẫu nhiên");
		pnCreateArray.add(btnRandom);
		btnRandom.setBackground(SystemColor.activeCaption);
		
		btnByHand = new JButton("B\u1EB1ng tay");
		btnByHand.setBounds(160, 140, 109, 25);
		btnByHand.setToolTipText("Tạo mảng theo ý muốn");
		pnCreateArray.add(btnByHand);
		btnByHand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Input byHand = new Input();
				byHand.setVisible(true);
				setState(3);
			}
		});
		btnByHand.setBackground(SystemColor.activeCaption);
		btnRandom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createRandom();
			}
		});
		pnArray.setLayout(gl_pnArray);
		pnTool.setLayout(gl_pnTool);
		
		pnArrayArea = new JPanel();
		pnArrayArea.setBackground(SystemColor.control);
		pnArrayArea.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Khung ch\u1EA1y m\u00F4 ph\u1ECFng", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnArrayArea.setBounds(5, 44, 1355, 360);
		contentPane.add(pnArrayArea);
				pnArrayArea.setLayout(null);
				lbPoint1.setBounds(50, 25, 50, 25);
                
				pnArrayArea.add(lbPoint1);
                lbPoint1.setOpaque(true);
                lbPoint1.setFont(new Font("Helvetica", Font.BOLD, 17));
                lbPoint1.setBackground(SystemColor.menu);
                lbPoint1.setHorizontalAlignment(SwingConstants.CENTER);
                lbPoint1.setVerticalAlignment(SwingConstants.CENTER);
                lbPoint2.setBounds(50, 25, 50, 25);
                
                pnArrayArea.add(lbPoint2);
                lbPoint2.setOpaque(true);
                lbPoint2.setFont(new Font("Helvetica", Font.BOLD, 17));
                lbPoint2.setBackground(SystemColor.menu);
                lbPoint2.setHorizontalAlignment(SwingConstants.CENTER);
                lbPoint2.setVerticalAlignment(SwingConstants.CENTER);
                lbPointM.setBounds(50, 25, 50, 25);
                
                pnArrayArea.add(lbPointM);
                lbPointM.setOpaque(true);
                lbPointM.setFont(new Font("Helvetica", Font.BOLD, 17));
                lbPointM.setBackground(SystemColor.menu);
                lbPointM.setHorizontalAlignment(SwingConstants.CENTER);
                lbPointM.setVerticalAlignment(SwingConstants.CENTER);
		
		grSort = new ButtonGroup();
		grSort.add(rdBubbleSort);
		grSort.add(rdQuickSort);
		grSort.add(rdMergeSort);
		
		btnAboutButton = new JButton("About");
		btnAboutButton.setBounds(46, 151, 83, 31);
		btnAboutButton.setToolTipText("Thông tin thêm");
		pnAlgorithm.add(btnAboutButton);
		btnAboutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(contentPane, " Đồ Án Phân Tích Và Thiết Kế Giải Thuật. \n Sinh Viên 1: Ngô Văn Huy \n Sinh Viên 2: Trần Ngọc Mậu \n Giáo viên hướng dẫn: Đỗ Phúc Hảo \n SĐT : 0856787657 \n Email: huyngoit99@gmail.com","Thông Tin",1);
			}
		});
		btnAboutButton.setBackground(SystemColor.activeCaption);
		
		ButtonGroup grDirect = new ButtonGroup();
		grDirect.add(rdDecrease);
		grDirect.add(rdIncrease);
		rdIncrease.setSelected(true);
		
		/*
		 * Các sự kiện Action Listener cho tất cả các thuật toán sắp xếp.
		 * gọi các sự kiện khi sắp xếp.
		 */
		
		eBubbleSort = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		    	  model.removeAllElements();
		    	  addCodeBubbleSort();	
		      }
		};
		
		eQuickSort = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
		    	  model.removeAllElements();
		    	  addCodeQuickSort();
			}
		};
		
		eMergeSort = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
		    	  model.removeAllElements();
		    	  addCodeMergeSort();
			}
		};
		rdBubbleSort.addActionListener(eBubbleSort);
		rdQuickSort.addActionListener(eQuickSort);
		rdMergeSort.addActionListener(eMergeSort);
		
		eSize = new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				lsCode.setFont(new Font("Monospaced",Font.BOLD,slSize.getValue()));
				lsCode.revalidate();
				lsCode.repaint();
		    }
		};
		
		slSize.addChangeListener(eSize);
                
                eSpeed = new ChangeListener() {
                    public void stateChanged(ChangeEvent e) {
                        time = 100 - slSpeed.getValue() * 10;		//slSpeed.getValue() : trả về giá trị hiện tại của thanh trượt
                    }
                };
                
                slSpeed.addChangeListener(eSpeed);
		
		eIncrease = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		    	  //set Increase or Decrease ( Tăng dần hoặc giảm dần )
		    	  isIncrease = true;
		      }
		};
		
		eDecrease = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		    	  isIncrease = false;
		      }
		};
		
		rdIncrease.addActionListener(eIncrease);
		rdDecrease.addActionListener(eDecrease);
		setState(0);
	}
	
	/*
	 * Đặt các trạng thái và trình quản lý giao diện . 
	 */
	public void setState(int state) {
		switch (state) {
		case 0: //Trạng thái đầu tiên, chưa tạo mảng.
			btnCreateArray.setEnabled(true);
			btnDeleteArray.setEnabled(false);
			btnSetZero.setEnabled(false);

			btnRandom.setEnabled(false);
			btnByHand.setEnabled(true);

			rdBubbleSort.setEnabled(true);
			rdQuickSort.setEnabled(true);
			rdMergeSort.setEnabled(true);
			
			rdIncrease.setEnabled(true);
			rdDecrease.setEnabled(true);
			
			btnSort.setEnabled(false);
			btnStop.setEnabled(true);
			break;
			
		case 1: //Tạo mảng, đang đợi set giá trị cho mảng.
			btnDeleteArray.setEnabled(true);
			btnSetZero.setEnabled(true);
			
			btnRandom.setEnabled(true);
			btnByHand.setEnabled(true);

			rdBubbleSort.setEnabled(true);
			rdQuickSort.setEnabled(true);
			rdMergeSort.setEnabled(true);
			
			rdIncrease.setEnabled(true);
			rdDecrease.setEnabled(true);
			break;
			
		case 2: //Set giá trị, sẵng sàng sắp xếp.
			btnDeleteArray.setEnabled(true);
			btnSetZero.setEnabled(true);

			btnRandom.setEnabled(true);

			rdBubbleSort.setEnabled(true);
			rdQuickSort.setEnabled(true);
			rdMergeSort.setEnabled(true);
			
			rdIncrease.setEnabled(true);
			rdDecrease.setEnabled(true);
			
			btnSort.setEnabled(true);
			btnStop.setEnabled(false);
			break;
			
		case 3: //Đang sắp xếp.
			btnCreateArray.setEnabled(true);
			btnDeleteArray.setEnabled(true);
			btnSetZero.setEnabled(false);
			
			btnRandom.setEnabled(false);
			btnByHand.setEnabled(false);

			rdIncrease.setEnabled(false);
			rdDecrease.setEnabled(false);

			rdBubbleSort.setEnabled(false);
			rdQuickSort.setEnabled(false);
			rdMergeSort.setEnabled(false);
			
			btnSort.setEnabled(false);
			btnStop.setEnabled(true);
			break;
			
		case 4: //Sắp xếp xong .
			btnCreateArray.setEnabled(true);
			btnDeleteArray.setEnabled(true);
			btnSetZero.setEnabled(true);
			
			btnRandom.setEnabled(true);
			btnByHand.setEnabled(true);

			rdBubbleSort.setEnabled(true);
			rdQuickSort.setEnabled(true);
			rdMergeSort.setEnabled(true);
			
			rdIncrease.setEnabled(true);
			rdDecrease.setEnabled(true);
			
			btnSort.setEnabled(true);
			btnStop.setEnabled(true);
			break;
			default:
				btnCreateArray.setEnabled(true);
				btnDeleteArray.setEnabled(false);
				btnSetZero.setEnabled(false);

				btnRandom.setEnabled(false);
				btnByHand.setEnabled(true);

				rdBubbleSort.setEnabled(true);
				rdQuickSort.setEnabled(true);
				rdMergeSort.setEnabled(true);
				
				rdIncrease.setEnabled(true);
				rdDecrease.setEnabled(true);
				
				btnSort.setEnabled(false);
				btnStop.setEnabled(false);
		}
	}
	
	/*
	 * Làm việc với mảng.
	 */
	public void createArrays() {
		
		//Xóa các mảng trước nếu có và set số phần tử cho mảng.
		deleteArrays();
		num = (Integer)spNum.getValue();
		lbArrays = new JLabel[num];
		array = new int[num];
		
		for (int i = 0; i < num; i++) {
			
			//Tạo các label, set giá  trị = 0.
			lbArrays[i] = new JLabel("0");
			array[i] = 0;
			pnArrayArea.add(lbArrays[i]);
			lbArrays[i].setText(String.valueOf(array[i]));
			
			//set kích thước cho các label.
			lbArrays[i].setSize(50,50);
			lbArrays[i].setOpaque(true);
			lbArrays[i].setForeground(Color.BLACK);
			
			//set vị trí cho các label
			if (i == 0)
				lbArrays[i].setLocation(((int) ((18 - num) * 0.5) * 70) + 100, 150);
			else
				lbArrays[i].setLocation(lbArrays[i-1].getX() + 70, 150);
			
			//set phông chữ.
			lbArrays[i].setFont(new Font("Tahoma", Font.PLAIN, 30));
			
			//set màu nền.
			lbArrays[i].setBackground(SystemColor.inactiveCaption);
			//Căn chỉnh các text trong label.
			lbArrays[i].setHorizontalAlignment(SwingConstants.CENTER); 
			lbArrays[i].setVerticalAlignment(SwingConstants.CENTER);
		}
                
                pnArrayArea.add(lbPoint1);
                pnArrayArea.add(lbPoint2);
                pnArrayArea.add(lbPointM);
                
		pnArrayArea.setVisible(true);		//Làm hiển thị (True) hoặc ẩn (False).
		pnArrayArea.validate();				//Xác nhận thành phần này và các thành phần con của nó.
		pnArrayArea.repaint();
		setState(1);
	}
	
	public void deleteArrays() {
		for (int i = 0; i < num; i++) {
			lbArrays[i].setText("0");
			array[i] = 0;
			lbArrays[i].setVisible(false);
			pnArrayArea.remove(lbArrays[i]);		//xóa mọi phần tử trong container đó.
		}
                
                lbPoint1.setText("");
                lbPoint2.setText("");
                lbPointM.setText("");
                pnArrayArea.remove(lbPoint1);
                pnArrayArea.remove(lbPoint2);
                pnArrayArea.remove(lbPointM);
		
		for (int i = 0; i < curT; i++) {
			try {
					threads[i].interrupt();
			} 
			catch (Exception e) {
				
			}
		}
		curT = -1;
		
		pnArrayArea.revalidate();
		pnArrayArea.repaint();
		setState(0);
	}
	
	public void setZero() {
		for (int i = 0; i < num; i++) {
			lbArrays[i].setText("0");
			array[i] = 0;
		}
		createArrays();
		pnArrayArea.revalidate();
		pnArrayArea.repaint();
		setState(1);
	}
	
	public void createRandom() {
		Random rand = new Random();
		for (int i = 0; i < num; i++) {
			int ranNum = rand.nextInt(100) + 0;
			lbArrays[i].setText(String.valueOf(ranNum));
			lbArrays[i].setForeground(Color.BLACK);
			array[i] = ranNum;
		}
		pnArrayArea.setVisible(true);
		pnArrayArea.validate();
		pnArrayArea.repaint();
		setState(2);
	}
	
	/*
	 * Code chạy minh họa khi mô phỏng.
	 */
	public void addCodeQuickSort() {
    	model.addElement("public static void quickSort(int[] a, int left, int right) {");
    	model.addElement("   int middle = left + (right - left) / 2;");
    	model.addElement("   int pivot = a[middle];");
    	model.addElement("   int i = left, j = right;");
    	model.addElement("   	while (i <= j) {");
    	model.addElement("        	while (a[i] < pivot) {");
    	model.addElement("          	i++;");
    	model.addElement("          }");
    	model.addElement("          while(a[i] < pivot) {");
    	model.addElement("              j--;");
    	model.addElement("          }");
    	model.addElement("   }");
    	model.addElement("      if(i <= j) {");
    	model.addElement("         Swap(a[i], a[j])");
    	model.addElement("   if(left < j)");
    	model.addElement("      QuickSort(a, left, j)");
    	model.addElement("   if(i < right)");
    	model.addElement("      QuickSort(a, i, right);");
    	model.addElement("	}");
    }
	
	public void addCodeBubbleSort() {
		model.addElement("public static void BubbleSort(int a[],int n) {");
		model.addElement("   int i, j;");
		model.addElement("   for (i = 0 ; i<n-1 ; i++)");
		model.addElement("        for (j =n-1; j >i ; j --)");
		model.addElement("             if(a[j] < a[j-1])");
		model.addElement("                 Swap(a[j], a[j-1]");
		model.addElement("}");
	}

    public void addCodeMergeSort() {
    	model.addElement("void MergeSort(int left, int right) {");
    	model.addElement("    if (left < right) {");
    	model.addElement("        int mid = (left + right) / 2;");
    	model.addElement("        MergeSort(left, mid);");
    	model.addElement("        MergeSort(mid + 1, right);");
    	model.addElement("        Merge(left, mid, right);");
    	model.addElement("    }");
    	model.addElement("}");
    	model.addElement("");
    	model.addElement("public void Merge(int left, int mid, int right) {");
    	model.addElement("    int n1 = mid - left + 1;");
    	model.addElement("    int n2 = right - mid;");
    	model.addElement("    int[] T = new int[n1 + n2];");
    	model.addElement("    int[] L = new int[n1];");
    	model.addElement("    int[] R = new int[n2];");
    	model.addElement("    int i, j, k;");
    	model.addElement("    for (i = 0; i < n1; i ++)");
    	model.addElement("        L[i] = array[left + i];");
    	model.addElement("    for (j = 0; j < n2; j ++)");
    	model.addElement("        R[j] = array[mid + 1 + j];");
    	model.addElement("    i = 0; j = 0;");
        model.addElement("    k = left;");
        model.addElement("    while (i < n1 && j < n2) {");
        model.addElement("        if (L[i] <= R[j]) {");
        model.addElement("            array[k] = L[i];");
        model.addElement("            i ++;");
        model.addElement("        } else {");
        model.addElement("            array[k] = R[j];");
        model.addElement("            j ++;");
        model.addElement("        }");
        model.addElement("        k ++;");
        model.addElement("    }");
        model.addElement("    while (i < n1) {");
        model.addElement("        array[k] = L[i];");
        model.addElement("        i ++;");
        model.addElement("        k ++;");
        model.addElement("    }");
        model.addElement("    while (j < n2) {");
        model.addElement("        array[k] = R[j];");
        model.addElement("        j ++;");
        model.addElement("        k ++;");
        model.addElement("    }");
        model.addElement("}");
    }
    /*
	 * Sắp xếp và hoán vị .
	 */
	public void Swap(JLabel lb1, JLabel lb2) {
		int x1 = lb1.getX();
		int x2 = lb2.getX();
		curT ++;
		
		int cur = curT;
		threads[cur] = new Thread(new Runnable() {
		    @Override
		    public void run() {
		    	try {
		    		if (cur != 0) {
			    		threads[cur-1].join();
			    	}
		    		
		    		lb1.setBackground(processingColor);
		    		lb2.setBackground(processingColor);
			        while (lb1.getY() > 100) {
			        	lb1.setLocation(lb1.getX(), lb1.getY() - 10);
			        	lb2.setLocation(lb2.getX(), lb2.getY() + 10);
                        lbPointM.setLocation(x2, lbPointM.getY() + 10);
			        	Thread.sleep(time);
			        }
			        while (lb1.getX() < x2) {
			        	lb1.setLocation(lb1.getX() + 10, lb1.getY());
			        	lb2.setLocation(lb2.getX() - 10, lb2.getY());
                        lbPointM.setLocation(lb2.getX(), 250);
			        	Thread.sleep(time);
			        }
			        while (lb1.getY() < 140) {
			        	lb1.setLocation(lb1.getX(), lb1.getY() + 10);
			        	lb2.setLocation(lb2.getX(), lb2.getY() - 10);
                        lbPointM.setLocation(x1, lbPointM.getY() - 10);
			        	Thread.sleep(time);
			        }
			        String txtLb1 = lb1.getText();
			        lb1.setText(lb2.getText());
			        lb2.setText(txtLb1);
			        lb1.setLocation(x1, 150);
		        	lb2.setLocation(x2, 150);
		        	lb1.setBackground(SystemColor.inactiveCaption);
		        	lb2.setBackground(SystemColor.inactiveCaption);
		    	} catch (Exception e) {
		    	}
		    }
		});
		threads[cur].start();
	}

    	public void Move(JLabel lb1, JLabel lb2, int pos) {
            int x1 = lb1.getX();
            int x2 = lb2.getX();
            curT ++;
            
            int cur = curT;
            threads[cur] = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        if (cur != 0) {
                            threads[cur - 1].join();
                        }
                        lb1.setOpaque(true);		//Nếu đúng, thành phần sẽ vẽ mọi pixel trong giới hạn của nó. 
                        lb2.setOpaque(true);		//Ngược lại, thành phần có thể không vẽ một số hoặc tất cả các pixel của nó, cho phép các pixel bên dưới hiển thị xuyên qua.
                        lb1.setBackground(SystemColor.activeCaption);
                        lb2.setBackground(SystemColor.activeCaption);
                        while (lb1.getY() > 100) {
                            lb1.setLocation(lb1.getX(), lb1.getY() - 10);
                            Thread.sleep(time);
                        }
                        while (lb1.getX() > x2) {
                            lb2.setLocation(lb2.getX() + 10, lb2.getY());
                            lb1.setLocation(lb1.getX() - 10, lb1.getY());
                            Thread.sleep(time);
                        }
                        while (pos == 0 && lb1.getY() < 150) {
                            lb1.setLocation(lb1.getX(), lb1.getY() + 10);
                            Thread.sleep(time);
                        }
                        String txtLb1 = lb1.getText();
                        lb1.setText(lb2.getText());
                        lb2.setText(txtLb1);
                        int y1 = lb1.getY();
                        lb1.setLocation(x1, lb2.getY());
                        lb2.setLocation(x2, y1);
                        lb1.setBackground(SystemColor.inactiveCaption);
                        if (pos == 0)
                            lb2.setBackground(SystemColor.inactiveCaption);
                   } catch (Exception e) {
                   }
               }
            });
            threads[cur].start();
        }
	
    /*
     * Code mẫu mô phỏng chạy theo chương trình.
     */
    public void highLight(int line) {
		curT++;
		
		int cur = curT;
		threads[cur] = new Thread(new Runnable() {
		    @Override
		    public void run() {
		    	try {
		    		if (cur != 0) {
			    		threads[cur-1].join();
			    	}
		    		lsCode.setSelectedIndex(line);		//bôi đen dòng được chọn trong lsCode.
                    lsCode.ensureIndexIsVisible(line); 	// tự cuộn đến dòng đang thực hiện.
                    lsCode.revalidate();				
                    lsCode.repaint();					//vẽ lại giá trị
		    		Thread.sleep(time);
		    	} catch (Exception e) {
		    		
		    	}
		    }
		});
		threads[cur].start();
	}
    
    /*
     * Đợi kết thúc sắp xếp.
     */
    public void waitSorted() {
    	curT++;
		
		int cur = curT;
		threads[cur] = new Thread(new Runnable() {
		    @Override
		    public void run() {
		    	try {
		    		if (cur != 0) {
			    		threads[cur-1].join();
			    	}
		    		setState(4);
		    		for (int i = 0; i < num; i++) {
		    			lbArrays[i].setForeground(Color.BLACK);
		    		}
                          lbPoint1.setText("");
                          lbPoint2.setText("");
                          lbPointM.setText("");
                    JOptionPane.showMessageDialog(contentPane, "Đã sắp xếp xong", "Thông Báo", 2);
		    	} catch (Exception e) {
		    		
		    	}
		    }
		});
		threads[cur].start();
    }

	/*
	 * Bubble Sort
	 */
	public void BubbleSort() {
		if (isIncrease) {
			highLight(1);
			int i, j;
			for (i = 0; i< num; i++) {
				highLight(2);
				for (j = num - 1; j > i; j--) {
					highLight(3);
					highLight(4);
					if(array[j]< array[j-1]) {
						int temp = array[j];
						array[j] = array[j - 1];
						array[j - 1] = temp;
						highLight(5);
						Swap(lbArrays[j - 1], lbArrays[j]);
					}
				}
			}
			highLight(0);
		} else {
			highLight(1);
			int i, j;
			for (i = 0; i< num; i++) {
				highLight(2);
				for (j = num - 1; j > i; j--) {
					highLight(3);
					highLight(4);
					if(array[j] > array[j-1]) {
						int temp = array[j];
						array[j] = array[j - 1];
						array[j - 1] = temp;
						highLight(5);
						Swap(lbArrays[j - 1], lbArrays[j]);
					}
				}
			}
			highLight(0);
		}
	}
    
    /*
     * QuickSort.
     */
    public void QuickSort() {
   
    	QuickSortAlgorithm(0, num - 1);
        actionQuickSort();
        step = 0;
    }
    
    //Set color cho pivot
    public void Coloring(JLabel lb1, Color c) {
        curT ++;
        System.out.println(curT);
        int cur = curT;
        threads[cur] = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (cur != 0)
                        threads[cur - 1].join();
                    lb1.setBackground(c);
                    Thread.sleep(time);
                } catch (Exception e) {}
            }
        });
        threads[cur].start();
    }
    
    public void Coloring(int left, int right, Color c) {
        curT ++;
        System.out.println(curT);
        int cur = curT;
        threads[cur] = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (cur != 0)
                        threads[cur - 1].join();
                    int i = left;
                    while (i <= right) {
                        if (i != (left + right) / 2)
                            lbArrays[i].setBackground(c);
                        i ++;
                    }
                    Thread.sleep(time);
                } catch (Exception e) {}
            }
        });
        threads[cur].start();
    }

    public void actionQuickSort() {
        int k, i, j;
        for (k = 0; k < step; k ++) {
        	highLight(2);
            i = lbI[k];
            j = lbJ[k];
            if (i != j) {
            	highLight(3);
                Coloring(lbArrays[(lbL[k] + lbR[k]) / 2], selectedGreen);
                highLight(5);
                Coloring(lbL[k], lbR[k], selectedYellow);
                highLight(13);
                Swap(lbArrays[i], lbArrays[j]);
            }
            if (lbL[k + 1] + lbR[k + 1] != lbL[k] + lbR[k]) {
                Coloring(lbArrays[(lbL[k] + lbR[k]) / 2], SystemColor.inactiveCaption);
                Coloring(lbL[k], lbR[k], SystemColor.inactiveCaption);
            }
        }
    }
    
    public void QuickSortAlgorithm(int left, int right) {
    
    	if (isIncrease) {
	    	int i, j, pivot;
	    	pivot = array[(left + right) / 2];
	        i = left; j = right;
	        while (i < j) {
	            while (array[i] < pivot) {
                        i ++;
                    }
	            while (array[j] > pivot) {
                        j --;
                    }
	            if (i <= j) {
                        if (array[i] != array[j]) {
	                    int temp = array[i];
	                    array[i] = array[j];
	                    array[j] = temp;
	                    if (i != j) {
                            lbL[step] = left; lbR[step] = right;
	                        lbI[step] = i; lbJ[step] = j;
	                        step ++;
	                    }
                        }
	                i ++;
	                j --;
	            }
	        }
	        if (left < j)
	        	QuickSortAlgorithm(left, j);
	        if (i < right)
	        	QuickSortAlgorithm(i, right);
        }
        else {
        	int i, j, pivot;
	        pivot = array[(left + right) / 2];
	        i = left; j = right;
	        while (i < j) {
	            while (array[i] > pivot) {
                        i ++;
                    }
	            while (array[j] < pivot) {
                        j --;
                    }
	            if (i <= j) {
                        if (array[i] != array[j]) {
	                    int temp = array[i];
	                    array[i] = array[j];
	                    array[j] = temp;
	                    if (i != j) {
                            lbL[step] = left; lbR[step] = right;
	                        lbI[step] = i; lbJ[step] = j;
	                        step ++;
	                    }
                        }
	                i ++;
	                j --;
	            }
	        }
	        if (left < j)
	        	QuickSortAlgorithm(left, j);
	        if (i < right)
	        	QuickSortAlgorithm(i, right);
        }
    }
    
    // <=======================================================>
    
    /*
     *  MergeSort.
     */
    //Xác định vị trí các phần tử khi đang sắp xếp.
    public void RelocateMerge(int left, int right, int[] T) {
        curT ++;
        System.out.println(curT);
        int cur = curT;
        threads[cur] = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (cur != 0)
                        threads[cur - 1].join();
                    for (int i = left; i <= right; i ++) {
                        if (lbArrays[i].getX() != oriLocat[i]) {
                            lbArrays[i].setLocation(oriLocat[i], 150);
                            lbArrays[i].setText(T[i - left]+" ");
                        }
                    }
                    Thread.sleep(time);
                } catch (Exception e) {
                }
            }
        });
        threads[cur].start();
    }
    public void PutUp(int left, int right) {
        curT ++;
        System.out.println(curT);
        int cur = curT;
        threads[cur] = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (cur != 0)
                        threads[cur - 1].join();
                    int mid = (left + right) / 2;
                    for (int i = left; i <= right; i ++) {
                        if (i < mid + 1)
                            lbArrays[i].setBackground(selectedGreen);
                        else
                            lbArrays[i].setBackground(selectedYellow);
                    }
                    while (lbArrays[right].getY() > 50) {
                        for (int i = left; i <= right; i ++) {
                            if (lbArrays[i].getY() > 50)
                                lbArrays[i].setLocation(lbArrays[i].getX(), lbArrays[i].getY() - 10);
                        }
                        Thread.sleep(time);
                    }
                } catch (Exception e) {
                }
            }
        });
        threads[cur].start();
    }
    
    public void PutDown(JLabel lb1, int x, int y) {
        curT ++;
        System.out.println(curT);
        int cur = curT;
        threads[cur] = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (cur != 0) {
                        threads[cur - 1].join();
                    }
                    int x1 = lb1.getX();
                    lb1.setBackground(processingColor);
                    while (lb1.getY() < 100) {
                        lb1.setLocation(x1, lb1.getY() + 10);
                        Thread.sleep(time);
                    }
                    int y1 = lb1.getY();
                    if (x1 < x) {
                        while (lb1.getX() < x) {
                            lb1.setLocation(lb1.getX() + 10, y1);
                            Thread.sleep(time);
                        }
                        while (lb1.getY() < y) {
                            lb1.setLocation(x, lb1.getY() + 10);
                            Thread.sleep(time);
                        }
                    } else {
                        while (lb1.getX() > x) {
                            lb1.setLocation(lb1.getX() - 10, y1);
                            Thread.sleep(time);
                        }
                        while (lb1.getY() < y) {
                            lb1.setLocation(x, lb1.getY() + 10);
                            Thread.sleep(time);
                        }
                    }
                    lb1.setBackground(processingColor);
                } catch (Exception e) {
                }
            }
        });
        threads[cur].start();
    }
    
    public void Merge(int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        int[] T = new int[n1 + n2];
        int[] L = new int[n1];
        int[] R = new int[n2];
        int i, j, k;
        highLight(16);
        for (i = 0; i < n1; i ++)
            L[i] = array[left + i];
        highLight(18);
        for (j = 0; j < n2; j ++)
            R[j] = array[mid + 1 + j];
        PutUp(left, right);
        if (isIncrease) {
            i = 0; j = 0;
            k = left;
            while (i < n1 && j < n2) {
                highLight(22);
                highLight(23);
                if (L[i] <= R[j]) {
                    highLight(24);
                    array[k] = L[i];
                    PutDown(lbArrays[left + i], oriLocat[k], 150);
                    highLight(25);
                    i ++;
                } else {
                    highLight(27);
                    array[k] = R[j];
                    PutDown(lbArrays[mid + 1 + j], oriLocat[k], 150);
                    highLight(28);
                    j ++;
                }
                highLight(30);
                k ++;
            }
            while (i < n1) {
                highLight(32);
                highLight(33);
                array[k] = L[i];
                PutDown(lbArrays[left + i], oriLocat[k], 150);
                i ++;
                k ++;
            }
            while (j < n2) {
                highLight(37);
                highLight(38);
                array[k] = R[j];
                PutDown(lbArrays[mid + 1 + j], oriLocat[k], 150);
                j ++;
                k ++;
            }
        } else {
            i = 0; j = 0;
            k = left;
            while (i < n1 && j < n2) {
                highLight(22);
                highLight(23);
                if (L[i] >= R[j]) {
                    highLight(24);
                    array[k] = L[i];
                    PutDown(lbArrays[left + i], oriLocat[k], 150);
                    highLight(25);
                    i ++;
                } else {
                    highLight(27);
                    array[k] = R[j];
                    PutDown(lbArrays[mid + 1 + j], oriLocat[k], 150);
                    highLight(28);
                    j ++;
                }
                highLight(30);
                k ++;
            }
            while (i < n1) {
                highLight(32);
                highLight(33);
                array[k] = L[i];
                PutDown(lbArrays[left + i], oriLocat[k], 150);
                i ++;
                k ++;
            }
            while (j < n2) {
                highLight(37);
                highLight(38);
                array[k] = R[j];
                PutDown(lbArrays[mid + 1 + j], oriLocat[k], 150);
                j ++;
                k ++;
            }
        }
        for (i = 0; i < n1 + n2; i ++)
            T[i] = array[left + i];
        RelocateMerge(left, right, T);
    }
    
    public void MergeSortAlgorithm(int left, int right) {
        highLight(1);
        if (left < right) {
            highLight(2);
            int mid = (left + right) / 2;
            MergeSortAlgorithm(left, mid);
            MergeSortAlgorithm(mid + 1, right);
            Merge(left, mid, right);
        }
    }
    
    public void MergeSort() {
        for (int i = 0; i < num; i ++) 
            oriLocat[i] = lbArrays[i].getX();
        MergeSortAlgorithm(0, num - 1);
    }
    // <==========================================>
    
    public void setArray(int[] array) {
    	spNum.setValue(array.length);
    	deleteArrays();
		num = (Integer)spNum.getValue();
		
		lbArrays = new JLabel[num];
		this.array = array;
		
		for (int i = 0; i < num; i++) {
			
			//Tạo label, set giá trị = 0.
			lbArrays[i] = new JLabel(String.valueOf(array[i]));
			pnArrayArea.add(lbArrays[i]);
			
			//set kích thước label.
			lbArrays[i].setSize(50,50);
			lbArrays[i].setOpaque(true);
			lbArrays[i].setForeground(Color.BLUE);
			
			//set vị trí label.
			if (i == 0)
				lbArrays[i].setLocation(((int) ((18 - num) * 0.5) * 70) + 100, 150);
			else
				lbArrays[i].setLocation(lbArrays[i-1].getX() + 70, 150);
			
			//set phông chữ.
			lbArrays[i].setFont(new Font("Tahoma", Font.PLAIN, 30));
			
			//set background color
			lbArrays[i].setBackground(SystemColor.inactiveCaption);
			
			//Căn chỉnh text .
			lbArrays[i].setHorizontalAlignment(SwingConstants.CENTER); 
			lbArrays[i].setVerticalAlignment(SwingConstants.CENTER);
		}
		pnArrayArea.setVisible(true);
		pnArrayArea.validate();
		pnArrayArea.repaint();
		setState(2);
    }
    
    /*
     * Kiểm tra sắp xếp.
     */
    public boolean isSorted() {
    	if (isIncrease) {
    		for (int i = 0; i < array.length - 1; i++)
    			if (array[i] > array[i+1])
    				return false;
    	}	
    	else {
    		for (int i = 0; i < array.length - 1; i++)
    			if (array[i] < array[i+1])
    				return false;
    	}
    	return true;
    }
    
/*
 * Luồng xác định lại vị trí sau khi sắp xếp.    
 */
    public void threadReLocate() {
    	
    	curT++;
		int cur = curT;
		threads[cur] = new Thread(new Runnable() {
		    @Override
		    public void run() {
		    	try {
		    		if (cur != 0) {
			    		threads[cur-1].join();
			    	}
		    		for (int i = 0; i < num; i++) {
		    			if (i == 0)
		    				lbArrays[i].setLocation(((int) ((18 - num) * 0.5) * 70) + 100, 150);
		    			else
		    				lbArrays[i].setLocation(lbArrays[i-1].getX() + 70, 150);
		    		}
		    	} catch (Exception e) {
		    		
		    	}
		    }
		});
		threads[cur].start();
    }
}

