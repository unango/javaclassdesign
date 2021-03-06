import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

public class divison extends JFrame implements ActionListener {
	public divison() {
		conmysql.sqlsel(this, table, "select *  from division",len);
	}
	public static void main(String[] args) {
		 try
		    {
			 	BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
		        org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
		        
		    }
		    catch(Exception e)
		    {
		        //TODO exception
		    }
		 divison dome = new divison();
		dome.create();
	}
	
	JFrame f = new JFrame("部门管理");
	JButton b1 = new JButton("添加新部门");
	JButton b2 = new JButton("修改部门信息");
	JButton b3 = new JButton("删除部门信息");
	JButton b4 = new JButton("查询所有部门信息");
	JButton b5 = new JButton("返回");
	
	String[] enss = {"divisionid","divisionname","mainname","funame"};
	int len=enss.length;
	JTextField tf[] = new JTextField[len];
	String[] cloum = { "部门编号", "部门名称", "主任", "副主任"};
	Object[][] row = new Object[50][len];
	JTable table = new JTable(row, cloum);
	JScrollPane scrollpane = new JScrollPane(table);
	JSplitPane splitpane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

	void create() {
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 
		JPanel p = (JPanel) f.getContentPane();
		p.setLayout(new FlowLayout());
		p.add(scrollpane);
		p.add(splitpane);
		JPanel p1 = new JPanel();
		p1.add(b1);
		p1.add(b2);
		p1.add(b3);
		p1.add(b4);
		p1.add(b5);
		JPanel p2 = new JPanel();
		//p2.setBackground(Color.cyan);
		p2.add(scrollpane);
		p.setLayout(new FlowLayout());
		p.add(new JLabel(""));
		
		
		for(int i=0;i<len;i++)
		{
			tf[i] =new JTextField(10);
			p.add(new JLabel(cloum[i]));
			p.add(tf[i]);
		}
		
		splitpane.add(p1, splitpane.TOP);
		splitpane.add(p2, splitpane.BOTTOM);
		splitpane.setDividerLocation(50);
		//p.setBackground(Color.CYAN);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		f.setBounds(550, 70, 530, 650);
		f.setResizable(true);
// 可以调整界面大小   
		f.setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {
		if (b1.equals(e.getSource())) { // 添加新员工信息
			
			String str = "INSERT INTO division (divisionid,divisionname,mainname,funame)"
					+ "VALUES(";
			for(int i=0;i<len;i++)
			{				
				if(i!=0)str+=",";
				str+="'"+tf[i].getText()+"'";
			}
			str+=");";
			//System.out.println(str);
			
			conmysql.sqlexe(this,str,"录入成功!","录入失败!");
		}
		if (b2.equals(e.getSource())) {// 修改员工信息
			if(tf[0].getText().isEmpty())JOptionPane.showMessageDialog(this, "请输入员工号");
			else
			{
				String str= "UPDATE division SET  ";
				for(int i=1;i<len;i++)
				{
					if(!tf[i].getText().isEmpty())str+=enss[i]+"='"+tf[i].getText()+"',";
				}
				
				str=str.substring(0,str.length()-1)+" where divisionid='" + tf[0].getText() + "';";
				//System.out.println(str);
				conmysql.sqlexe(this,str,"修改成功!","修改失败!");
				
			}
		}
		if (b3.equals(e.getSource())) {// 删除员工信息 
			String str="DELETE  FROM  division where divisionid='" + tf[0].getText() + "';";
			conmysql.sqlexe(this, str, "删除成功", "删除失败");
		}
		if (b4.equals(e.getSource())) {// 查询全部员工信息 
			conmysql.sqlsel(this, table, "select *  from division",len);
		}
		if (b5.equals(e.getSource())) {// 返回
			Salarymanageface gl = new Salarymanageface();
			gl.create();
			f.dispose();
		}
	}
}