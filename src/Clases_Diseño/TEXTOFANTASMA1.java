package Clases_Diseño;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;

public class TEXTOFANTASMA1 extends JLabel implements FocusListener, DocumentListener {

    private static final long serialVersionUID = 1L;

    public enum Show {
        ALWAYS, FOCUS_GAINED, FOCUS_LOST;
    }

    private JTextComponent component;
    private Document document;

    private Show show;
    private boolean showPromptOnce;
    private int focusLost;

    public TEXTOFANTASMA1(String text, JTextComponent component) {
        this(text, component, Show.ALWAYS);
    }

    public TEXTOFANTASMA1(String text, JTextComponent component, Show show) {
        this.component = component;
        setShow(show);
        document = component.getDocument();

        setText(text);
        setFont(component.getFont());

        setForeground(Color.WHITE);

        setHorizontalAlignment(JLabel.LEADING);

        component.addFocusListener(this);
        document.addDocumentListener(this);

        component.setLayout(new BorderLayout());
        component.add(this);
        checkForPrompt();
    }

    public void changeAlpha(float alpha) {
        changeAlpha((int) (alpha * 255));
    }

    public void changeAlpha(int alpha) {
        alpha = alpha > 255 ? 255 : alpha < 0 ? 0 : alpha;

        Color foreground = getForeground();
        int red = foreground.getRed();
        int green = foreground.getGreen();
        int blue = foreground.getBlue();

        Color withAlpha = new Color(red, green, blue, alpha);
        super.setForeground(withAlpha);
    }

    public void changeStyle(int style) {
        setFont(getFont().deriveFont(style));
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public boolean getShowPromptOnce() {
        return showPromptOnce;
    }

    public void setShowPromptOnce(boolean showPromptOnce) {
        this.showPromptOnce = showPromptOnce;
    }

    private void checkForPrompt() {

        if (document.getLength() > 0) {
            setVisible(false);
            return;
        }
        if (showPromptOnce && focusLost > 0) {
            setVisible(false);
            return;
        }
        if (component.hasFocus()) {
            if (show == Show.ALWAYS || show == Show.FOCUS_GAINED) {
                setVisible(true);
            } else {
                setVisible(false);
            }
        } else {
            if (show == Show.ALWAYS || show == Show.FOCUS_LOST) {
                setVisible(true);
            } else {
                setVisible(false);
            }
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        checkForPrompt();
    }

    @Override
    public void focusLost(FocusEvent e) {
        focusLost++;
        checkForPrompt();
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        checkForPrompt();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        checkForPrompt();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
    }

}
