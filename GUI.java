/* Author: Ethan Leung
 * NetID: eleung6
 * Project 1: URCalculator
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI extends JFrame {
JButton add, sub, div, mult, clear, del, equal, dot, mod, sin, cos, tan, and, or, not, less, great, parenL, parenR, pow, unmin, calc;
JButton num[];
JTextField output;
String previous, current;

public GUI() {
super("Calculator");
JPanel panel = new JPanel();
current = "";
previous = "";

JPanel row1 = new JPanel();
JPanel row2 = new JPanel();
JPanel row3 = new JPanel();
JPanel row4 = new JPanel();
JPanel row5 = new JPanel();
JPanel row6 = new JPanel();

output = new JTextField(16);
add = new JButton("+");
sub = new JButton("-");
div = new JButton("/");
mult = new JButton("*");
mod = new JButton("%");
clear = new JButton("C");
del = new JButton("D");
equal = new JButton("=");
dot = new JButton(".");
sin = new JButton("sin");
cos = new JButton("cos");
tan = new JButton("tan");
and = new JButton("&");
or = new JButton("|");
not = new JButton("!");
less = new JButton("<");
great = new JButton(">");
parenL = new JButton("(");
parenR = new JButton(")");
pow = new JButton("^");
unmin = new JButton("~");
calc = new JButton("CALCULATE");

click click = new click();

output.setFont(new Font("Monospaced", Font.BOLD, 22));
add.setFont(new Font("Monospaced", Font.BOLD, 22));
sub.setFont(new Font("Monospaced", Font.BOLD, 22));
div.setFont(new Font("Monospaced", Font.BOLD, 22));
mult.setFont(new Font("Monospaced", Font.BOLD, 22));
mod.setFont(new Font("Monospaced", Font.BOLD, 22));
clear.setFont(new Font("Monospaced", Font.BOLD, 22));
del.setFont(new Font("Monospaced", Font.BOLD, 22));
equal.setFont(new Font("Monospaced", Font.BOLD, 22));
dot.setFont(new Font("Monospaced", Font.BOLD, 22));
sin.setFont(new Font("Monospaced", Font.BOLD, 22));
cos.setFont(new Font("Monospaced", Font.BOLD, 22));
tan.setFont(new Font("Monospaced", Font.BOLD, 22));
and.setFont(new Font("Monospaced", Font.BOLD, 22));
or.setFont(new Font("Monospaced", Font.BOLD, 22));
not.setFont(new Font("Monospaced", Font.BOLD, 22));
less.setFont(new Font("Monospaced", Font.BOLD, 22));
great.setFont(new Font("Monospaced", Font.BOLD, 22));
parenL.setFont(new Font("Monospaced", Font.BOLD, 22));
parenR.setFont(new Font("Monospaced", Font.BOLD, 22));
pow.setFont(new Font("Monospaced", Font.BOLD, 22));
unmin.setFont(new Font("Monospaced", Font.BOLD, 22));
calc.setFont(new Font("Monospaced", Font.BOLD, 22));

num = new JButton[11];
for (int i = 0; i < num.length - 1; i++) {
	num[i] = new JButton(String.valueOf(i));
	num[i].setFont(new Font("Monospaced", Font.BOLD, 22));
	num[i].addActionListener(click);
}

output.setMaximumSize(new Dimension(395, 40));
output.setFont(new Font("Monospaced", Font.BOLD, 27));
output.setDisabledTextColor(new Color(0, 0, 0));
output.setMargin(new Insets(0, 5, 0, 0));
output.setText("0");

output.addActionListener(click);
add.addActionListener(click);
sub.addActionListener(click);
div.addActionListener(click);
mult.addActionListener(click);
mod.addActionListener(click);
clear.addActionListener(click);
del.addActionListener(click);
equal.addActionListener(click);
dot.addActionListener(click);
sin.addActionListener(click);
cos.addActionListener(click);
tan.addActionListener(click);
and.addActionListener(click);
or.addActionListener(click);
not.addActionListener(click);
less.addActionListener(click);
great.addActionListener(click);
parenL.addActionListener(click);
parenR.addActionListener(click);
pow.addActionListener(click);
unmin.addActionListener(click);
calc.addActionListener(click);

row1.setLayout(new BoxLayout(row1, BoxLayout.LINE_AXIS));
row2.setLayout(new BoxLayout(row2, BoxLayout.LINE_AXIS));
row3.setLayout(new BoxLayout(row3, BoxLayout.LINE_AXIS));
row4.setLayout(new BoxLayout(row4, BoxLayout.LINE_AXIS));
row5.setLayout(new BoxLayout(row5, BoxLayout.LINE_AXIS));
row6.setLayout(new BoxLayout(row6, BoxLayout.LINE_AXIS));

row1.add(Box.createHorizontalGlue());
row1.add(clear);
row1.add(del);
row1.add(unmin);
row1.add(add);

row2.add(sin);
row2.add(and);
row2.add(great);
row2.add(parenL);
row2.add(num[1]);
row2.add(num[2]);
row2.add(num[3]);
row2.add(sub);

row3.add(cos);
row3.add(or);
row3.add(less);
row3.add(parenR);
row3.add(num[4]);
row3.add(num[5]);
row3.add(num[6]);
row3.add(mult);

row4.add(tan);
row4.add(not);
row4.add(equal);
row4.add(pow);
row4.add(num[7]);
row4.add(num[8]);
row4.add(num[9]);
row4.add(div);

row5.add(Box.createHorizontalGlue());
row5.add(num[0]);
row5.add(dot);
row5.add(mod);

row6.add(Box.createHorizontalGlue());
row6.add(calc);

panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
panel.add(output);
panel.add(Box.createRigidArea(new Dimension(0, 6)));
panel.add(row1);
panel.add(row2);
panel.add(row3);
panel.add(row4);
panel.add(row5);
panel.add(row6);

this.add(panel);
this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
this.setVisible(true);
this.setSize(417,325);
}

public void delete() {
	if (current.length() > 0) {
        current = current.substring(0, current.length() - 1);
    }
}

public void clear() {
	 current = "";
     previous = "";
}

public void updateOutput(){
	output.setText(current);
}

public void appendToOutput(String input) {
	if (input.equals(".") && current.contains(".")) {
        return;
    }
	current += input;
}

public void calculate() {
	current = Calculation.calc(current);
}

private class click implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton selectedBtn = (JButton) e.getSource();
	    for (JButton btn : num) {
	        if (selectedBtn == btn) {
	            appendToOutput(btn.getText());
	            updateOutput();
	        }
	    }
        if (selectedBtn == add) {
        	appendToOutput(add.getText());
        } else if (selectedBtn == sub) {
        	appendToOutput(sub.getText());
        } else if (selectedBtn == mult) {
        	appendToOutput(mult.getText());
        } else if (selectedBtn == div) {
        	appendToOutput(div.getText());
        } else if (selectedBtn == dot) {
        	appendToOutput(dot.getText());
        } else if (selectedBtn == mod) {
        	appendToOutput(mod.getText());
        } else if (selectedBtn == sin) {
        	appendToOutput(sin.getText());
        } else if (selectedBtn == cos) {
        	appendToOutput(cos.getText());
        } else if (selectedBtn == tan) {
        	appendToOutput(tan.getText());
        } else if (selectedBtn == pow) {
        	appendToOutput(pow.getText());
        } else if (selectedBtn == less) {
        	appendToOutput(less.getText());
        } else if (selectedBtn == great) {
        	appendToOutput(great.getText());
        } else if (selectedBtn == equal) {
        	appendToOutput(equal.getText());
        } else if (selectedBtn == and) {
        	appendToOutput(and.getText());
        } else if (selectedBtn == or) {
        	appendToOutput(or.getText());
        } else if (selectedBtn == parenL) {
        	appendToOutput(parenL.getText());
        } else if (selectedBtn == parenR) {
        	appendToOutput(parenR.getText());
        } else if (selectedBtn == unmin) {
        	appendToOutput(unmin.getText());
        } else if (selectedBtn == not) {
            appendToOutput(not.getText());
        } else if (selectedBtn == del) {
        	delete();
        } else if (selectedBtn == clear) {
        	clear();
        } else if (selectedBtn == calc) {
        	calculate();
        }
        updateOutput();
        if (current.equals("ERROR")) {
    		current = "";
    	} else if (current.equals(Calculation.calc(current))) {
    		current = "";
    	}
    }
		
}

public static class Calculation<E> extends URCalculator<E> {
	public static String calc(String input) {
		try {
			if (URCalculator.postFixEval(input) == null) {
				throw new NullPointerException();
			}
		} catch (Exception e) {
			return "ERROR";
		}
		return (URCalculator.postFixEval(input)).toString();
	}
	
}

public static void main(String[] args) {
	new GUI();	
}

}





