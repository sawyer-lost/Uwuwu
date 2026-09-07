import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private final Simulator simulator;
    private final JTextArea sourceEditor;
    private final JTextArea traceArea;
    private final JTextArea cpuArea;
    private JLabel statusLabel;
    private JLabel instructionLabel;
    private JLabel pcLabel;
    private int stepNumber = 1;
    private boolean programLoaded = false;

    private static final Color BG = new Color(18, 20, 27);
    private static final Color PANEL = new Color(27, 30, 39);
    private static final Color PANEL_2 = new Color(33, 36, 47);
    private static final Color BORDER = new Color(57, 61, 75);
    private static final Color TEXT = new Color(235, 238, 245);
    private static final Color MUTED = new Color(160, 166, 180);
    private static final Color ACCENT = new Color(105, 125, 255);

    public Main() {
        simulator = new Simulator();

        JFrame frame = new JFrame("STC89C52 Microcontroller Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 760);
        frame.setMinimumSize(new Dimension(1000, 650));
        frame.setLocationRelativeTo(null);

        JPanel root = new JPanel(new BorderLayout(12, 12));
        root.setBackground(BG);
        root.setBorder(new EmptyBorder(14, 14, 14, 14));
        frame.setContentPane(root);

        root.add(createHeader(), BorderLayout.NORTH);

        sourceEditor = createEditor();
        traceArea = createOutputArea();
        cpuArea = createOutputArea();

        sourceEditor.setText(
                "MOV A,#02\n" +
                "MOV R0,#04\n" +
                "XCH A,R0\n" +
                "ADD A,#03\n" +
                "INC A\n" +
                "DEC A\n" +
                "ANL A,#0F\n" +
                "ORL A,#01\n" +
                "SJMP 1\n" +
                "INC A\n" +
                "CLR A\n" +
                "END");

        JPanel center = new JPanel(new GridLayout(1, 3, 12, 0));
        center.setOpaque(false);
        center.add(createPanel("ASSEMBLY PROGRAM", createEditorScroll()));
        center.add(createPanel("EXECUTION TRACE", createTraceScroll()));
        center.add(createPanel("CPU STATE", createCpuScroll()));
        root.add(center, BorderLayout.CENTER);

        root.add(createBottomBar(), BorderLayout.SOUTH);

        updateDisplay();
        frame.setVisible(true);
    }

    private JPanel createHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);

        JLabel title = new JLabel("STC89C52  MICROCONTROLLER  SIMULATOR");
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setForeground(TEXT);

        JLabel subtitle = new JLabel("Week 2  •  8051 Architecture  •  Instruction Execution");
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 12));
        subtitle.setForeground(MUTED);

        JPanel titles = new JPanel();
        titles.setOpaque(false);
        titles.setLayout(new BoxLayout(titles, BoxLayout.Y_AXIS));
        titles.add(title);
        titles.add(Box.createVerticalStrut(3));
        titles.add(subtitle);
        header.add(titles, BorderLayout.WEST);

        JLabel team = new JLabel("ONJI BYTE");
        team.setFont(new Font("SansSerif", Font.BOLD, 13));
        team.setForeground(ACCENT);
        team.setBorder(new CompoundBorder(
                new LineBorder(ACCENT, 1, true),
                new EmptyBorder(6, 10, 6, 10)));
        header.add(team, BorderLayout.EAST);
        return header;
    }

    private JPanel createPanel(String title, JComponent component) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(PANEL);
        panel.setBorder(new CompoundBorder(
                new LineBorder(BORDER, 1, true),
                new EmptyBorder(10, 10, 10, 10)));

        JLabel label = new JLabel(title);
        label.setFont(new Font("SansSerif", Font.BOLD, 12));
        label.setForeground(TEXT);
        label.setBorder(new EmptyBorder(0, 2, 8, 0));
        panel.add(label, BorderLayout.NORTH);
        panel.add(component, BorderLayout.CENTER);
        return panel;
    }

    private JTextArea createEditor() {
        JTextArea area = new JTextArea();
        area.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 15));
        area.setForeground(new Color(220, 225, 238));
        area.setBackground(new Color(20, 23, 31));
        area.setCaretColor(Color.WHITE);
        area.setSelectionColor(new Color(65, 75, 105));
        area.setLineWrap(false);
        area.setTabSize(4);
        area.setBorder(new EmptyBorder(10, 10, 10, 10));
        return area;
    }

    private JTextArea createOutputArea() {
        JTextArea area = new JTextArea();
        area.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        area.setForeground(new Color(220, 225, 238));
        area.setBackground(new Color(20, 23, 31));
        area.setCaretColor(Color.WHITE);
        area.setLineWrap(false);
        area.setEditable(false);
        area.setBorder(new EmptyBorder(10, 10, 10, 10));
        return area;
    }

    private JScrollPane createEditorScroll() {
        JScrollPane scroll = new JScrollPane(sourceEditor);
        styleScroll(scroll);
        return scroll;
    }

    private JScrollPane createTraceScroll() {
        JScrollPane scroll = new JScrollPane(traceArea);
        styleScroll(scroll);
        return scroll;
    }

    private JScrollPane createCpuScroll() {
        JScrollPane scroll = new JScrollPane(cpuArea);
        styleScroll(scroll);
        return scroll;
    }

    private void styleScroll(JScrollPane scroll) {
        scroll.setBorder(new LineBorder(BORDER, 1, true));
        scroll.getViewport().setBackground(new Color(20, 23, 31));
        scroll.getVerticalScrollBar().setUnitIncrement(14);
    }

    private JPanel createBottomBar() {
        JPanel bottom = new JPanel(new BorderLayout(10, 8));
        bottom.setOpaque(false);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        buttons.setOpaque(false);

        JButton load = createButton("LOAD", ACCENT);
        JButton reset = createButton("RESET", new Color(75, 80, 95));
        JButton step = createButton("STEP", new Color(75, 80, 95));
        JButton run = createButton("RUN", new Color(75, 80, 95));

        buttons.add(load);
        buttons.add(reset);
        buttons.add(step);
        buttons.add(run);

        load.addActionListener(e -> loadFromEditor());
        reset.addActionListener(e -> resetSimulator());
        step.addActionListener(e -> stepOnce());
        run.addActionListener(e -> runProgram());

        JPanel info = new JPanel(new GridLayout(2, 1));
        info.setOpaque(false);

        instructionLabel = new JLabel("Instruction: -");
        instructionLabel.setForeground(MUTED);
        instructionLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));

        statusLabel = new JLabel("●  READY");
        statusLabel.setForeground(new Color(90, 210, 145));
        statusLabel.setFont(new Font("SansSerif", Font.BOLD, 12));

        info.add(instructionLabel);
        info.add(statusLabel);

        pcLabel = new JLabel("PC: 0000");
        pcLabel.setForeground(MUTED);
        pcLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));

        bottom.add(buttons, BorderLayout.WEST);
        bottom.add(info, BorderLayout.CENTER);
        bottom.add(pcLabel, BorderLayout.EAST);
        return bottom;
    }

    private JButton createButton(String text, Color background) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.BOLD, 12));
        button.setForeground(Color.WHITE);
        button.setBackground(background);
        button.setFocusPainted(false);
        button.setBorder(new CompoundBorder(
                new LineBorder(BORDER, 1, true),
                new EmptyBorder(8, 18, 8, 18)));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    private String[] getProgramFromEditor() {
        String[] rawLines = sourceEditor.getText().split("\\R");
        List<String> program = new ArrayList<>();
        for (String line : rawLines) {
            line = line.trim();
            if (!line.isEmpty()) program.add(line);
        }
        return program.toArray(new String[0]);
    }

    private void loadFromEditor() {
        String[] program = getProgramFromEditor();
        if (program.length == 0) {
            showError("Enter at least one instruction.");
            return;
        }
        try {
            simulator.loadProgram(program);
            programLoaded = true;
            stepNumber = 1;
            traceArea.setText("PROGRAM LOADED\n" + "────────────────────────────────\n" + formatProgram(program));
            updateDisplay();
        } catch (IllegalArgumentException ex) {
            programLoaded = false;
            showError("Invalid program:\n" + ex.getMessage());
            updateDisplay();
        }
    }

    private String formatProgram(String[] program) {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < program.length; i++) {
            text.append(String.format("%04X   %s%n", i, program[i]));
        }
        return text.toString();
    }

    private void stepOnce() {
        if (!ensureLoaded()) return;
        if (isFinished()) {
            showMessage("Program finished. Press RESET to start again.");
            return;
        }
        simulator.step();
        appendStepTrace();
        stepNumber++;
        updateDisplay();
    }

    private void runProgram() {
        if (!ensureLoaded()) return;
        if (isFinished()) {
            showMessage("Program finished. Press RESET to start again.");
            return;
        }

        int safety = 0;
        while (safety < 1000 && !isFinished()) {
            simulator.step();
            appendStepTrace();
            stepNumber++;
            safety++;
            String status = simulator.getExecutionStatus();
            if (status.startsWith("Execution error") || status.startsWith("Unsupported instruction")) break;
        }
        updateDisplay();
    }

    private void appendStepTrace() {
        Instruction instruction = simulator.getCurrentInstruction();
        traceArea.append("\nSTEP " + stepNumber + "\n");
        traceArea.append("────────────────────────────────\n");
        if (instruction != null) {
            traceArea.append("Instruction : " + instruction.getFullInstruction() + "\n");
            traceArea.append("Category    : " + instruction.getCategory() + "\n\n");
        }
        traceArea.append(simulator.getExecutionTrace());
        traceArea.append("STATUS : " + simulator.getExecutionStatus() + "\n");
        traceArea.append("════════════════════════════════\n");
        traceArea.setCaretPosition(traceArea.getDocument().getLength());
    }

    private void resetSimulator() {
        simulator.reset();
        programLoaded = false;
        stepNumber = 1;
        traceArea.setText("READY\n\nEdit the assembly program and press LOAD.\nThen use STEP or RUN to execute it.");
        updateDisplay();
    }

    private void updateDisplay() {
        cpuArea.setText(simulator.getCPU().getState());
        Instruction instruction = simulator.getCurrentInstruction();
        instructionLabel.setText("Instruction: " + (instruction == null ? "-" : instruction.getFullInstruction()));

        String status = simulator.getExecutionStatus();
        statusLabel.setText("●  " + status.toUpperCase());
        if (status.toLowerCase().contains("error")) statusLabel.setForeground(new Color(235, 100, 100));
        else if (status.toLowerCase().contains("terminated") || status.toLowerCase().contains("finished")) statusLabel.setForeground(new Color(90, 210, 145));
        else statusLabel.setForeground(new Color(255, 190, 80));

        pcLabel.setText(String.format("PC: %04X", simulator.getCPU().getPC()));
    }

    private boolean ensureLoaded() {
        if (!programLoaded) {
            showMessage("Load the assembly program first.");
            return false;
        }
        return true;
    }

    private boolean isFinished() {
        String status = simulator.getExecutionStatus();
        return status.equals("Program terminated") || status.equals("Program finished");
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "STC89C52 Simulator", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "STC89C52 Simulator - Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) { }
        SwingUtilities.invokeLater(Main::new);
    }
}
        label.setForeground(TEXT);
        label.setBorder(new EmptyBorder(0, 2, 8, 0));
        panel.add(label, BorderLayout.NORTH);
        panel.add(component, BorderLayout.CENTER);
        return panel;
    }

    private JTextArea createEditor() {
        JTextArea area = new JTextArea();
        area.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 15));
        area.setForeground(new Color(220, 225, 238));
        area.setBackground(new Color(20, 23, 31));
        area.setCaretColor(Color.WHITE);
        area.setSelectionColor(new Color(65, 75, 105));
        area.setLineWrap(false);
        area.setTabSize(4);
        area.setBorder(new EmptyBorder(10, 10, 10, 10));
        return area;
    }

    private JTextArea createOutputArea() {
        JTextArea area = new JTextArea();
        area.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        area.setForeground(new Color(220, 225, 238));
        area.setBackground(new Color(20, 23, 31));
        area.setCaretColor(Color.WHITE);
        area.setLineWrap(false);
        area.setEditable(false);
        area.setBorder(new EmptyBorder(10, 10, 10, 10));
        return area;
    }

    private JScrollPane createEditorScroll() {
        JScrollPane scroll = new JScrollPane(sourceEditor);
        styleScroll(scroll);
        return scroll;
    }

    private JScrollPane createTraceScroll() {
        JScrollPane scroll = new JScrollPane(traceArea);
        styleScroll(scroll);
        return scroll;
    }

    private JScrollPane createCpuScroll() {
        JScrollPane scroll = new JScrollPane(cpuArea);
        styleScroll(scroll);
        return scroll;
    }

    private void styleScroll(JScrollPane scroll) {
        scroll.setBorder(new LineBorder(BORDER, 1, true));
        scroll.getViewport().setBackground(new Color(20, 23, 31));
        scroll.getVerticalScrollBar().setUnitIncrement(14);
    }

    private JPanel createBottomBar() {
        JPanel bottom = new JPanel(new BorderLayout(10, 8));
        bottom.setOpaque(false);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        buttons.setOpaque(false);

        JButton load = createButton("LOAD", ACCENT);
        JButton reset = createButton("RESET", new Color(75, 80, 95));
        JButton step = createButton("STEP", new Color(75, 80, 95));
        JButton run = createButton("RUN", new Color(75, 80, 95));

        buttons.add(load);
        buttons.add(reset);
        buttons.add(step);
        buttons.add(run);

        load.addActionListener(e -> loadFromEditor());
        reset.addActionListener(e -> resetSimulator());
        step.addActionListener(e -> stepOnce());
        run.addActionListener(e -> runProgram());

        JPanel info = new JPanel(new GridLayout(2, 1));
        info.setOpaque(false);

        instructionLabel = new JLabel("Instruction: -");
        instructionLabel.setForeground(MUTED);
        instructionLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));

        statusLabel = new JLabel("●  READY");
        statusLabel.setForeground(new Color(90, 210, 145));
        statusLabel.setFont(new Font("SansSerif", Font.BOLD, 12));

        info.add(instructionLabel);
        info.add(statusLabel);

        pcLabel = new JLabel("PC: 0000");
        pcLabel.setForeground(MUTED);
        pcLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));

        bottom.add(buttons, BorderLayout.WEST);
        bottom.add(info, BorderLayout.CENTER);
        bottom.add(pcLabel, BorderLayout.EAST);
        return bottom;
    }

    private JButton createButton(String text, Color background) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.BOLD, 12));
        button.setForeground(Color.WHITE);
        button.setBackground(background);
        button.setFocusPainted(false);
        button.setBorder(new CompoundBorder(
                new LineBorder(BORDER, 1, true),
                new EmptyBorder(8, 18, 8, 18)));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    private String[] getProgramFromEditor() {
        String[] rawLines = sourceEditor.getText().split("\\R");
        List<String> program = new ArrayList<>();
        for (String line : rawLines) {
            line = line.trim();
            if (!line.isEmpty()) program.add(line);
        }
        return program.toArray(new String[0]);
    }

    private void loadFromEditor() {
        String[] program = getProgramFromEditor();
        if (program.length == 0) {
            showError("Enter at least one instruction.");
            return;
        }
        try {
            simulator.loadProgram(program);
            programLoaded = true;
            stepNumber = 1;
            traceArea.setText("PROGRAM LOADED\n" + "────────────────────────────────\n" + formatProgram(program));
            updateDisplay();
        } catch (IllegalArgumentException ex) {
            programLoaded = false;
            showError("Invalid program:\n" + ex.getMessage());
            updateDisplay();
        }
    }

    private String formatProgram(String[] program) {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < program.length; i++) {
            text.append(String.format("%04X   %s%n", i, program[i]));
        }
        return text.toString();
    }

    private void stepOnce() {
        if (!ensureLoaded()) return;
        if (isFinished()) {
            showMessage("Program finished. Press RESET to start again.");
            return;
        }
        simulator.step();
        appendStepTrace();
        stepNumber++;
        updateDisplay();
    }

    private void runProgram() {
        if (!ensureLoaded()) return;
        if (isFinished()) {
            showMessage("Program finished. Press RESET to start again.");
            return;
        }

        int safety = 0;
        while (safety < 1000 && !isFinished()) {
            simulator.step();
            appendStepTrace();
            stepNumber++;
            safety++;
            String status = simulator.getExecutionStatus();
            if (status.startsWith("Execution error") || status.startsWith("Unsupported instruction")) break;
        }
        updateDisplay();
    }

    private void appendStepTrace() {
        Instruction instruction = simulator.getCurrentInstruction();
        traceArea.append("\nSTEP " + stepNumber + "\n");
        traceArea.append("────────────────────────────────\n");
        if (instruction != null) {
            traceArea.append("Instruction : " + instruction.getFullInstruction() + "\n");
            traceArea.append("Category    : " + instruction.getCategory() + "\n\n");
        }
        traceArea.append(simulator.getExecutionTrace());
        traceArea.append("STATUS : " + simulator.getExecutionStatus() + "\n");
        traceArea.append("════════════════════════════════\n");
        traceArea.setCaretPosition(traceArea.getDocument().getLength());
    }

    private void resetSimulator() {
        simulator.reset();
        programLoaded = false;
        stepNumber = 1;
        traceArea.setText("READY\n\nEdit the assembly program and press LOAD.\nThen use STEP or RUN to execute it.");
        updateDisplay();
    }

    private void updateDisplay() {
        cpuArea.setText(simulator.getCPU().getState());
        Instruction instruction = simulator.getCurrentInstruction();
        instructionLabel.setText("Instruction: " + (instruction == null ? "-" : instruction.getFullInstruction()));

        String status = simulator.getExecutionStatus();
        statusLabel.setText("●  " + status.toUpperCase());
        if (status.toLowerCase().contains("error")) statusLabel.setForeground(new Color(235, 100, 100));
        else if (status.toLowerCase().contains("terminated") || status.toLowerCase().contains("finished")) statusLabel.setForeground(new Color(90, 210, 145));
        else statusLabel.setForeground(new Color(255, 190, 80));

        pcLabel.setText(String.format("PC: %04X", simulator.getCPU().getPC()));
    }

    private boolean ensureLoaded() {
        if (!programLoaded) {
            showMessage("Load the assembly program first.");
            return false;
        }
        return true;
    }

    private boolean isFinished() {
        String status = simulator.getExecutionStatus();
        return status.equals("Program terminated") || status.equals("Program finished");
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "STC89C52 Simulator", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "STC89C52 Simulator - Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) { }
        SwingUtilities.invokeLater(Main::new);
    }
}
