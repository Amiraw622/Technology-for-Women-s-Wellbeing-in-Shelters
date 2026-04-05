package UI;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * Shelter Wellness Companion — Discreet Lifestyle App
 *
 * Home screen has 3 innocent options:
 * 1. Talk to Me (free casual chat)
 * 2. Today's Music (daily music recommendation)
 * 3. Today's Recipe (daily food recipe)
 *
 * After viewing music or recipe detail → "Does this match your mood?"
 * YES → "Enjoy your day!"
 * NO → gentle inquiry → 3 support options:
 * Help & Support | Music & Exercise | Talk
 */
public class ShelterWellnessApp extends JFrame {

    private CardLayout cardLayout;
    private JPanel cardPanel;
    private Image[] animals;

    static final Color BG_PRIMARY = new Color(255, 255, 255);
    static final Color BG_SECONDARY = new Color(35, 30, 48);
    static final Color TEXT_PRIMARY = new Color(140, 66, 45);
    static final Color TEXT_SECONDARY = new Color(170, 120, 135);
    static final Color TEXT_MUTED = new Color(180, 122, 130);
    static final Color ACCENT_TEAL = new Color(103, 20, 65);
    static final Color ACCENT_WARM = new Color(39, 0, 100);
    static final Color ACCENT_ROSE = new Color(220, 20, 40);
    static final Color ACCENT_PINK = new Color(232, 63, 86);
    static final Color ACCENT_PURPLE = new Color(140, 80, 180);
    static final Color ACCENT_CORAL = new Color(216, 90, 48);
    static final Color CARD_BG = new Color(255, 255, 255, 10);
    static final Color CARD_HOVER = new Color(255, 255, 255, 20);
    static final Color CARD_BORDER = new Color(255, 255, 255, 20);

    static final Font FONT_TITLE = new Font("SansSerif", Font.BOLD, 26);
    static final Font FONT_SUBTITLE = new Font("SansSerif", Font.PLAIN, 14);
    static final Font FONT_CARD_TITLE = new Font("SansSerif", Font.BOLD, 17);
    static final Font FONT_CARD_DESC = new Font("SansSerif", Font.PLAIN, 13);
    static final Font FONT_BODY = new Font("SansSerif", Font.PLAIN, 14);
    static final Font FONT_SMALL = new Font("SansSerif", Font.PLAIN, 12);
    static final Font FONT_BUTTON = new Font("SansSerif", Font.BOLD, 14);
    static final Font FONT_CHAT = new Font("SansSerif", Font.PLAIN, 14);
    static final Font FONT_DETAIL_TITLE = new Font("SansSerif", Font.BOLD, 22);

    static final String[][] DAILY_MUSIC = {
            { "Morning Calm", "Relaxing music to help you find peace",
                    "A peaceful piano piece with gentle dynamics.\nClose your eyes, breathe slowly,\nand let the melody carry you." },
            { "Afternoon Breeze", "Relaxing music to help you find peace",
                    "Acoustic fingerpicking layered with\nbirdsong and a gentle stream.\nPerfect for a mindful break." },
            { "Sunset Glow", "Relaxing music to help you find peace",
                    "Warm pads and soft chimes that\nfade like the last light of day.\nLet your shoulders drop and relax." },
            { "Rainy Day Comfort", "Relaxing music to help you find peace",
                    "The steady rhythm of raindrops\naccompanied by a cello melody.\nWrap yourself in something cozy." },
            { "Garden Morning", "Relaxing music to help you find peace",
                    "A wooden flute dances over\na carpet of birdsong and rustling leaves.\nImagine sunshine on your face." },
    };

    static final String[][] DAILY_RECIPES = {
            { "Honey Lemon Tea", "Warm, soothing, and easy to make",
                    "Ingredients:\n  - 1 cup hot water\n  - 1 tbsp honey\n  - Juice of half a lemon\n\nStir honey into hot water.\nAdd lemon juice. Sip slowly." },
            { "Banana Oat Pancakes", "Simple, healthy, and comforting",
                    "Ingredients:\n  - 1 ripe banana\n  - 1/2 cup oats\n  - 1 egg\n  - Pinch of cinnamon\n\nMash banana, mix all together.\nCook small pancakes on low heat." },
            { "Veggie Soup", "Nourishing and warming for the soul",
                    "Ingredients:\n  - 2 carrots, 1 potato, 1 onion\n  - 4 cups broth\n  - Salt, pepper, herbs\n\nChop veggies, simmer in broth\n20 min until tender." },
            { "Fruit & Yogurt Bowl", "Fresh, light, and energizing",
                    "Ingredients:\n  - 1 cup yogurt\n  - Handful of berries\n  - 1 tbsp granola\n  - Drizzle of honey\n\nLayer yogurt, fruit, granola.\nDrizzle honey on top." },
            { "Cinnamon Toast", "Quick comfort with a warm aroma",
                    "Ingredients:\n  - 2 slices bread\n  - Butter\n  - Cinnamon + sugar\n\nToast bread, spread butter.\nSprinkle cinnamon sugar.\nEnjoy the warm aroma." },
    };

    private int todayMusic, todayRecipe;

    public ShelterWellnessApp() {
        super("A small space for you");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 720);
        setMinimumSize(new Dimension(820, 650));
        setLocationRelativeTo(null);

        animals = new Image[] {
                new ImageIcon("public/images/animal/drink.png").getImage(),
                new ImageIcon("public/images/animal/eat.PNG").getImage(),
                new ImageIcon("public/images/animal/first.PNG").getImage(),
                new ImageIcon("public/images/animal/move.PNG").getImage(),
                new ImageIcon("public/images/animal/music.PNG").getImage(),
                new ImageIcon("public/images/animal/rest.PNG").getImage(),
                new ImageIcon("public/images/animal/shy.PNG").getImage(),
                new ImageIcon("public/images/animal/stretch.png").getImage(),
                new ImageIcon("public/images/animal/talk.PNG").getImage(),
                new ImageIcon("public/images/animal/think.PNG").getImage()
        };

        Random rand = new Random();
        todayMusic = rand.nextInt(DAILY_MUSIC.length);
        todayRecipe = rand.nextInt(DAILY_RECIPES.length);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setBackground(BG_PRIMARY);

        cardPanel.add(createHomeScreen(), "home");
        cardPanel.add(createDetailScreen(DAILY_MUSIC[todayMusic][0], DAILY_MUSIC[todayMusic][1],
                DAILY_MUSIC[todayMusic][2], "\u266B", ACCENT_WARM, "TODAY'S MUSIC", "\u25B6  Play now"), "musicdetail");
        cardPanel.add(createDetailScreen(DAILY_RECIPES[todayRecipe][0], DAILY_RECIPES[todayRecipe][1],
                DAILY_RECIPES[todayRecipe][2], "\u2615", ACCENT_ROSE, "TODAY'S RECIPE", "\u2665  Show me another one"),
                "recipedetail");
        cardPanel.add(createSupportChoiceScreen(), "supportChoice");
        cardPanel.add(createHelpResourcesScreen(), "help");
        cardPanel.add(buildChatScreen("Talk", ACCENT_CORAL, true, "home"), "talk");
        cardPanel.add(buildChatScreen("Chat", ACCENT_TEAL, false, "home"), "freechat");

        add(cardPanel);
        cardLayout.show(cardPanel, "home");
    }

    private JPanel createSupportChoiceScreen() {
        return new GradientPanel() {

            final Rectangle calmBtn = new Rectangle();
            final Rectangle comfortBtn = new Rectangle();
            final Rectangle distractBtn = new Rectangle();
            final Rectangle talkBtn = new Rectangle();

            int hov = -1;

            {
                MouseAdapter ma = new MouseAdapter() {

                    public void mouseMoved(MouseEvent e) {
                        hov = calmBtn.contains(e.getPoint()) ? 0
                                : comfortBtn.contains(e.getPoint()) ? 1
                                        : distractBtn.contains(e.getPoint()) ? 2
                                                : talkBtn.contains(e.getPoint()) ? 3 : -1;
                        repaint();
                    }

                    public void mouseClicked(MouseEvent e) {
                        if (calmBtn.contains(e.getPoint()))
                            navigate("musicdetail");
                        else if (comfortBtn.contains(e.getPoint()))
                            navigate("recipedetail");
                        else if (distractBtn.contains(e.getPoint()))
                            navigate("musicdetail");
                        else if (talkBtn.contains(e.getPoint()))
                            navigate("talk");
                    }

                    public void mouseExited(MouseEvent e) {
                        hov = -1;
                        repaint();
                    }
                };

                addMouseListener(ma);
                addMouseMotionListener(ma);
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = setup(g);

                int w = getWidth();
                int cx = w / 2;

                g2.setFont(FONT_TITLE);
                ctr(g2, "What would feel most helpful right now?", cx, 120);

                int btnH = 64;
                int startY = 165;
                int gapY = 28;
                int leftX = 200;
                int rightX = 360;
                int bubbleW = 330;

                String[] texts = {
                        "Want to stretch with me for a minute?",
                        "Can we take a slow breath together?",
                        "Maybe a sip of water first?",
                        "Want to change your spot with me?"
                };

                Rectangle[] btns = { calmBtn, comfortBtn, distractBtn, talkBtn };

                Image[] chosen = {
                        animals[7],
                        animals[9],
                        animals[0],
                        animals[3]
                };

                for (int i = 0; i < 4; i++) {
                    int y = startY + i * (btnH + gapY);
                    boolean left = (i % 2 == 0);

                    int curX = left ? leftX : rightX;
                    btns[i].setBounds(curX, y, bubbleW, btnH);

                    Image img = chosen[i];

                    g2.setColor(hov == i ? alphaColor(ACCENT_PURPLE, 24) : CARD_BG);
                    g2.fillRoundRect(curX, y, bubbleW, btnH, 24, 24);

                    g2.setColor(alphaColor(ACCENT_PURPLE, hov == i ? 90 : 55));
                    g2.setStroke(new BasicStroke(1.2f));
                    g2.drawRoundRect(curX, y, bubbleW, btnH, 24, 24);

                    Polygon tail = new Polygon();

                    if (left) {
                        tail.addPoint(curX, y + 22);
                        tail.addPoint(curX - 16, y + 30);
                        tail.addPoint(curX, y + 38);
                    } else {
                        tail.addPoint(curX + bubbleW, y + 22);
                        tail.addPoint(curX + bubbleW + 16, y + 30);
                        tail.addPoint(curX + bubbleW, y + 38);
                    }

                    g2.setColor(hov == i ? alphaColor(ACCENT_PURPLE, 24) : CARD_BG);
                    g2.fillPolygon(tail);

                    g2.setColor(alphaColor(ACCENT_PURPLE, hov == i ? 90 : 55));
                    g2.drawPolygon(tail);

                    g2.setColor(TEXT_PRIMARY);
                    g2.setFont(FONT_BUTTON);
                    ctr(g2, texts[i], curX + bubbleW / 2, y + 39);

                    int targetH;

                    if (i == 0)
                        targetH = 120; // 第一张放大一点
                    else if (i == 2)
                        targetH = 115;
                    else if (i == 3)
                        targetH = 125; // 第四张稍微放大
                    else
                        targetH = 100; // 其他保持
                    int imgW = img.getWidth(this);
                    int imgH = img.getHeight(this);
                    int targetW = (int) (imgW * (targetH / (double) imgH));

                    int offset = 105;

                    int animalY = y + btnH / 2 - targetH / 2;

                    int gap;

                    if (i == 0)
                        gap = 0; 
                    else if (i == 3)
                        gap = 0;
                    else
                        gap = 18;

                    if (left) {
                        int animalX = curX - gap - targetW;
                        g2.drawImage(img, animalX, animalY, targetW, targetH, this);
                    } else {
                        int animalX = curX + bubbleW + gap + targetW;
                        g2.drawImage(img, animalX, animalY, -targetW, targetH, this);
                    }
                }

                g2.dispose();
            }
        };
    }

    // ═══════ HOME — 4 cards + help link ═══════
    // Replace your entire createHomeScreen() method with this:
    private JPanel createHomeScreen() {
        return new GradientPanel() {
            int hov = -1;
            final Rectangle[] cards = { new Rectangle(), new Rectangle(), new Rectangle(), new Rectangle() };
            final Rectangle helpLink = new Rectangle(); // separate from cards!
            {
                addMouseMotionListener(new MouseMotionAdapter() {
                    public void mouseMoved(MouseEvent e) {
                        int prev = hov;
                        hov = -1;
                        for (int i = 0; i < 4; i++)
                            if (cards[i].contains(e.getPoint())) {
                                hov = i;
                                break;
                            }
                        if (helpLink.contains(e.getPoint()))
                            hov = 99;
                        setCursor(
                                hov >= 0 ? Cursor.getPredefinedCursor(Cursor.HAND_CURSOR) : Cursor.getDefaultCursor());
                        if (prev != hov)
                            repaint();
                    }
                });
                addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        if (cards[0].contains(e.getPoint()))
                            navigate("freechat");
                        else if (cards[1].contains(e.getPoint()))
                            navigate("musicdetail");
                        else if (cards[2].contains(e.getPoint()))
                            navigate("recipedetail");
                        else if (cards[3].contains(e.getPoint()))
                            navigate("supportChoice");
                        else if (helpLink.contains(e.getPoint()))
                            navigate("help");
                    }

                    public void mouseExited(MouseEvent e) {
                        if (hov != -1) {
                            hov = -1;
                            repaint();
                        }
                    }
                });
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = setup(g);
                int w = getWidth(), cx = w / 2;
                drawAnimal(g2, cx, 50, 0.95f, true);
                g2.setFont(FONT_TITLE);
                g2.setColor(new Color(140, 76, 45));
                ctr(g2, "Here's something gentle for you today \uD83D\uDC9B", cx, 218);
                g2.setFont(FONT_SUBTITLE);
                g2.setColor(TEXT_MUTED);

                int cW = Math.min(340, w - 48), cX = (w - cW) / 2, cH = 70, gap = 18, y = 240;

                drawCard(g2, cX, y, cW, cH, hov == 0, ACCENT_TEAL);
                cards[0].setBounds(cX, y, cW, cH);
                cardIcon(g2, cX, y, "\u2661", ACCENT_TEAL, hov == 0);
                cardText(g2, cX, y, "Talk to me",
                        "I'm always here for you", ACCENT_TEAL, hov == 0);

                y += cH + gap;
                drawCard(g2, cX, y, cW, cH, hov == 1, ACCENT_WARM);
                cards[1].setBounds(cX, y, cW, cH);
                cardIcon(g2, cX, y, "\u266B", ACCENT_WARM, hov == 1);
                cardText(g2, cX, y, "Listen", "Wanna listen to some music?",
                        ACCENT_WARM, hov == 1);

                y += cH + gap;
                drawCard(g2, cX, y, cW, cH, hov == 2, ACCENT_ROSE);
                cards[2].setBounds(cX, y, cW, cH);
                cardIcon(g2, cX, y, "\u2615", ACCENT_ROSE, hov == 2);
                cardText(g2, cX, y, "Cook", "Find something to cook?", ACCENT_ROSE, hov == 2);

                y += cH + gap;
                drawCard(g2, cX, y, cW, cH, hov == 3, ACCENT_PURPLE);
                cards[3].setBounds(cX, y, cW, cH);
                cardIcon(g2, cX, y, "\u2728", ACCENT_PURPLE, hov == 3);
                cardText(g2, cX, y, "Do something", "Find what feels right", ACCENT_PURPLE, hov == 3);

                g2.setFont(FONT_SMALL);
                g2.setColor(new Color(180, 120, 130));
                ctr(g2, "\"Take a deep breath.\"", cx, getHeight() - 36);

                // Help & Support link — uses helpLink, NOT cards[3]
                int linkY = getHeight() - 70;
                String helpText = "Help & Support";
                g2.setFont(FONT_BODY);
                g2.setColor(hov == 99 ? new Color(200, 50, 70) : ACCENT_PINK);

                FontMetrics fm = g2.getFontMetrics();
                int textW = fm.stringWidth(helpText);
                int linkX = cx - textW / 2;

                g2.drawString(helpText, linkX, linkY);
                g2.drawLine(linkX, linkY + 2, linkX + textW, linkY + 2);

                helpLink.setBounds(linkX, linkY - 16, textW, 24);
                g2.dispose();
            }
        };
    }

    // ═══════ HELP RESOURCES (redesigned with international numbers) ═══════
    // Replace your entire createHelpResourcesScreen() method with this:
    private JPanel createHelpResourcesScreen() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(new Color(255, 252, 248));

        // ─── Warm top bar ───
        JPanel top = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setPaint(
                        new GradientPaint(0, 0, new Color(255, 245, 240), 0, getHeight(), new Color(255, 235, 228)));
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.setColor(new Color(230, 190, 175, 80));
                g2.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
            }
        };
        top.setLayout(new BorderLayout());
        top.setPreferredSize(new Dimension(0, 60));

        JButton bk = new JButton("\u2190");
        bk.setFont(new Font("SansSerif", Font.BOLD, 18));
        bk.setForeground(new Color(180, 120, 100));
        bk.setOpaque(false);
        bk.setBorderPainted(false);
        bk.setContentAreaFilled(false);
        bk.setFocusPainted(false);
        bk.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bk.setPreferredSize(new Dimension(50, 60));
        bk.addActionListener(e -> navigate("home"));
        bk.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                bk.setForeground(new Color(140, 76, 45));
            }

            public void mouseExited(MouseEvent e) {
                bk.setForeground(new Color(180, 120, 100));
            }
        });
        top.add(bk, BorderLayout.WEST);

        JPanel titlePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                // transparent
            }
        };
        titlePanel.setOpaque(false);
        titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 16));
        JLabel tt = new JLabel("Help & Support");
        tt.setFont(new Font("SansSerif", Font.BOLD, 20));
        tt.setForeground(new Color(140, 76, 45));
        titlePanel.add(tt);
        top.add(titlePanel, BorderLayout.CENTER);
        p.add(top, BorderLayout.NORTH);

        // ─── Content ───
        JPanel content = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setPaint(
                        new GradientPaint(0, 0, new Color(255, 252, 248), 0, getHeight(), new Color(255, 243, 235)));
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(BorderFactory.createEmptyBorder(16, 24, 24, 24));

        // Privacy notice
        JPanel privacyCard = createWarmCard(
                "\uD83D\uDD12  Your privacy matters",
                "If you are in immediate danger, call emergency services.",
                new Color(180, 140, 120),
                new Color(255, 248, 242),
                new Color(240, 220, 210));
        content.add(privacyCard);
        content.add(Box.createVerticalStrut(20));

        // Section label
        JLabel secTitle = new JLabel("Emergency numbers by region");
        secTitle.setFont(new Font("SansSerif", Font.BOLD, 16));
        secTitle.setForeground(new Color(140, 76, 45));
        secTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        secTitle.setBorder(BorderFactory.createEmptyBorder(0, 4, 12, 0));
        content.add(secTitle);

        // Emergency numbers data
        String[][] emergencyNumbers = {
                { "\uD83C\uDDE8\uD83C\uDDF3", "China", "110", "Police" },
                { "\uD83C\uDDFA\uD83C\uDDF8", "United States", "911", "All emergencies" },
                { "\uD83C\uDDE8\uD83C\uDDE6", "Canada", "911", "All emergencies" },
                { "\uD83C\uDDE6\uD83C\uDDFA", "Australia", "000 / 112", "Emergency / mobile" },
                { "\uD83C\uDDF3\uD83C\uDDFF", "New Zealand", "111", "All emergencies" },
                { "\uD83C\uDDEE\uD83C\uDDF3", "India", "112 / 14490", "Emergency / women helpline" },
                { "\uD83C\uDDEC\uD83C\uDDE7", "United Kingdom", "999 / 112", "All emergencies" },
                { "\uD83C\uDDEA\uD83C\uDDFA", "Europe", "112", "Universal emergency" },
                { "\uD83C\uDDF8\uD83C\uDDEC", "Singapore", "999", "Police" },
                { "\uD83C\uDDEF\uD83C\uDDF5", "Japan", "110", "Police" },
                { "\uD83C\uDDF0\uD83C\uDDF7", "Korea", "112", "Police / emergency" },
                { "\uD83C\uDDF9\uD83C\uDDED", "Thailand", "191", "Police" },
                { "\uD83C\uDDF2\uD83C\uDDFE", "Malaysia", "999", "All emergencies" },
        };

        for (String[] entry : emergencyNumbers) {
            JPanel card = createEmergencyCard(entry[0], entry[1], entry[2], entry[3]);
            content.add(card);
            content.add(Box.createVerticalStrut(8));
        }

        content.add(Box.createVerticalStrut(16));

        // Support organizations section
        JLabel orgTitle = new JLabel("Support organizations");
        orgTitle.setFont(new Font("SansSerif", Font.BOLD, 16));
        orgTitle.setForeground(new Color(140, 76, 45));
        orgTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        orgTitle.setBorder(BorderFactory.createEmptyBorder(0, 4, 12, 0));
        content.add(orgTitle);

        String[][] orgs = {
                { "National DV Hotline (US)", "1-800-799-7233", "24/7 confidential support" },
                { "Crisis Text Line", "Text HOME to 741741", "Free crisis counseling" },
                { "RAINN (US)", "1-800-656-4673", "Sexual assault support" },
                { "Suicide & Crisis Lifeline", "988", "Call or text — 24/7" },
                { "Women's Law", "womenslaw.org", "Legal info for survivors" },
                { "NAMI Helpline", "1-800-950-6264", "Mental health support" },
        };

        for (String[] org : orgs) {
            JPanel card = createOrgCard(org[0], org[1], org[2]);
            content.add(card);
            content.add(Box.createVerticalStrut(8));
        }

        content.add(Box.createVerticalStrut(12));

        // Bottom encouragement
        JLabel footer = new JLabel("<html><div style='text-align:center;color:#B08878;font-size:11px;'>"
                + "You are not alone. Reaching out is a sign of strength.</div></html>");
        footer.setAlignmentX(Component.CENTER_ALIGNMENT);
        footer.setHorizontalAlignment(SwingConstants.CENTER);
        content.add(footer);

        JScrollPane sc = new JScrollPane(content);
        sc.setBorder(null);
        sc.getViewport().setOpaque(false);
        sc.setOpaque(false);
        sc.getVerticalScrollBar().setUnitIncrement(16);
        sc.getVerticalScrollBar().setPreferredSize(new Dimension(6, 0));
        p.add(sc, BorderLayout.CENTER);

        return p;
    }

    // Helper: emergency number card
    private JPanel createEmergencyCard(String flag, String country, String number, String desc) {
        JPanel card = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(255, 255, 255));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 16, 16);
                g2.setColor(new Color(235, 215, 205, 100));
                g2.setStroke(new BasicStroke(1f));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 16, 16);
                g2.dispose();
            }
        };
        card.setOpaque(false);
        card.setLayout(new BorderLayout(12, 0));
        card.setBorder(BorderFactory.createEmptyBorder(12, 16, 12, 16));
        card.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 64));

        // Left: flag + country
        JPanel left = new JPanel();
        left.setOpaque(false);
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        JLabel countryLabel = new JLabel(flag + "  " + country);
        countryLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        countryLabel.setForeground(new Color(100, 60, 45));
        left.add(countryLabel);
        JLabel descLabel = new JLabel(desc);
        descLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));
        descLabel.setForeground(new Color(170, 135, 125));
        left.add(descLabel);

        // Right: number
        JLabel numLabel = new JLabel(number);
        numLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        numLabel.setForeground(new Color(200, 80, 60));
        numLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        card.add(left, BorderLayout.CENTER);
        card.add(numLabel, BorderLayout.EAST);

        return card;
    }

    // Helper: support organization card
    private JPanel createOrgCard(String name, String contact, String desc) {
        JPanel card = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(255, 255, 255));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 16, 16);
                g2.setColor(new Color(225, 200, 210, 100));
                g2.setStroke(new BasicStroke(1f));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 16, 16);
                g2.dispose();
            }
        };
        card.setOpaque(false);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createEmptyBorder(12, 16, 12, 16));
        card.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        nameLabel.setForeground(new Color(100, 60, 45));
        card.add(nameLabel);

        JLabel contactLabel = new JLabel("\u260E  " + contact);
        contactLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
        contactLabel.setForeground(new Color(180, 90, 70));
        contactLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        card.add(contactLabel);

        JLabel descLabel = new JLabel(desc);
        descLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));
        descLabel.setForeground(new Color(170, 135, 125));
        card.add(descLabel);

        return card;
    }

    // Helper: warm info card
    private JPanel createWarmCard(String title, String body, Color textColor, Color bgColor, Color borderColor) {
        JPanel card = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(bgColor);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 16, 16);
                g2.setColor(borderColor);
                g2.setStroke(new BasicStroke(1f));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 16, 16);
                g2.dispose();
            }
        };
        card.setOpaque(false);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createEmptyBorder(12, 16, 12, 16));
        card.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
        titleLabel.setForeground(textColor);
        card.add(titleLabel);

        JLabel bodyLabel = new JLabel(body);
        bodyLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        bodyLabel.setForeground(new Color(textColor.getRed(), textColor.getGreen(), textColor.getBlue(), 180));
        card.add(bodyLabel);

        return card;
    }

    // ═══════ DETAIL (music or recipe) ═══════
    private JPanel createDetailScreen(String title, String subtitle, String body,
            String icon, Color accent, String label, String actionText) {
        return new GradientPanel() {
            int hov = -1;

            final Rectangle backBtn = new Rectangle();
            final Rectangle yesBtn = new Rectangle();
            final Rectangle noBtn = new Rectangle();
            final Rectangle actBtn = new Rectangle();

            {
                MouseAdapter ma = new MouseAdapter() {
                    @Override
                    public void mouseMoved(MouseEvent e) {
                        int p = hov;
                        hov = backBtn.contains(e.getPoint()) ? 0
                                : yesBtn.contains(e.getPoint()) ? 1
                                        : noBtn.contains(e.getPoint()) ? 2
                                                : actBtn.contains(e.getPoint()) ? 3
                                                        : -1;

                        setCursor(hov >= 0
                                ? Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
                                : Cursor.getDefaultCursor());

                        if (p != hov) {
                            repaint();
                        }
                    }

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (backBtn.contains(e.getPoint())) {
                            navigate("home");

                        } else if (yesBtn.contains(e.getPoint())) {
                            System.out.println("This works for the user.");

                        } else if (noBtn.contains(e.getPoint())) {
                            navigate("supportChoice");

                        } else if (actBtn.contains(e.getPoint())) {
                            if (label.equals("TODAY'S RECIPE")) {
                                todayRecipe = (todayRecipe + 1) % DAILY_RECIPES.length;
                                refreshRecipeDetail();
                                navigate("recipedetail");
                            } else if (label.equals("TODAY'S MUSIC")) {
                                todayMusic = (todayMusic + 1) % DAILY_MUSIC.length;
                                refreshMusicDetail();
                                navigate("musicdetail");
                            }
                        }
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        if (hov != -1) {
                            hov = -1;
                            repaint();
                        }
                    }
                };

                addMouseListener(ma);
                addMouseMotionListener(ma);
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = setup(g);

                int w = getWidth();
                int cx = w / 2;
                int cW = Math.min(380, w - 40);
                int cX = (w - cW) / 2;

                drawBack(g2, 20, 20, backBtn);

                g2.setFont(FONT_SMALL);
                g2.setColor(accent);
                ctr(g2, label, cx, 72);

                g2.setFont(new Font("SansSerif", Font.PLAIN, 48));
                g2.setColor(accent);
                ctr(g2, icon, cx, 120);

                g2.setFont(FONT_DETAIL_TITLE);
                g2.setColor(TEXT_PRIMARY);
                ctr(g2, title, cx, 160);

                g2.setFont(FONT_SUBTITLE);
                g2.setColor(TEXT_SECONDARY);
                ctr(g2, subtitle, cx, 184);

                int abW = 280, abH = 44, abX = cx - abW / 2, abY = 215;
                actBtn.setBounds(abX, abY, abW, abH);
                g2.setColor(hov == 3 ? alphaColor(accent, 35) : CARD_BG);
                g2.fillRoundRect(abX, abY, abW, abH, 20, 20);
                g2.setColor(alphaColor(accent, 60));
                g2.setStroke(new BasicStroke(1));
                g2.drawRoundRect(abX, abY, abW, abH, 20, 20);
                g2.setFont(FONT_BUTTON);
                g2.setColor(accent);
                ctr(g2, actionText, cx, abY + 29);

                int bY = 285, bH = 210;
                g2.setColor(CARD_BG);
                g2.fillRoundRect(cX, bY, cW, bH, 16, 16);
                g2.setColor(CARD_BORDER);
                g2.setStroke(new BasicStroke(0.5f));
                g2.drawRoundRect(cX, bY, cW, bH, 16, 16);

                g2.setFont(FONT_BODY);
                g2.setColor(TEXT_SECONDARY);
                int tY = bY + 28;
                for (String line : body.split("\n")) {
                    g2.drawString(line, cX + 20, tY);
                    tY += 22;
                }
                g2.setFont(new Font("SansSerif", Font.BOLD, 13));

                g2.setColor(hov == 2 ? ACCENT_TEAL : TEXT_SECONDARY);
                String hint = "Want something different?";

                int smallBtnW = 260;
                int smallBtnH = 38;
                int smallBtnX = cx - smallBtnW / 2;
                int smallBtnY = bY + bH + 20;

                noBtn.setBounds(smallBtnX, smallBtnY, smallBtnW, smallBtnH);

                g2.setColor(hov == 2 ? alphaColor(ACCENT_PURPLE, 28) : CARD_BG);
                g2.fillRoundRect(smallBtnX, smallBtnY, smallBtnW, smallBtnH, 18, 18);

                g2.setColor(alphaColor(ACCENT_PURPLE, hov == 2 ? 80 : 45));
                g2.drawRoundRect(smallBtnX, smallBtnY, smallBtnW, smallBtnH, 18, 18);

                g2.setFont(new Font("SansSerif", Font.BOLD, 13));
                g2.setColor(hov == 2 ? ACCENT_PURPLE : TEXT_SECONDARY);
                ctr(g2, hint, cx, smallBtnY + 24);
                g2.dispose();
            }
        };
    }

    // ═══════ CHAT (redesigned - warm & beautiful) ═══════
    private JPanel buildChatScreen(String label, Color accent, boolean sup, String backTo) {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(BG_PRIMARY);

        // ─── Top bar with animal + status ───
        JPanel top = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setPaint(
                        new GradientPaint(0, 0, new Color(255, 245, 240), 0, getHeight(), new Color(255, 235, 225)));
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.setColor(new Color(230, 190, 175, 80));
                g2.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);

                // Animal avatar
                int cx = getWidth() / 2;
                int imHeight = 122;
                int imWidth = 174;
                int avatarX = cx - imWidth / 2;
                int avatarY = 8;

                g2.drawImage(animals[8], avatarX, avatarY, imWidth, imHeight, null);

                // Name + status
                int textX = avatarX + imWidth - 26;
                g2.setFont(new Font("SansSerif", Font.BOLD, 15));
                g2.setColor(new Color(140, 76, 45));
                g2.drawString("Your friend", textX, 35);
                g2.setFont(new Font("SansSerif", Font.PLAIN, 11));
                g2.setColor(new Color(180, 140, 130));
                g2.drawString("always here for you \u2665", textX, 50);

                g2.dispose();
            }
        };

        top.setLayout(new BorderLayout());
        top.setPreferredSize(new Dimension(0, 128));

        // Back button (warm style)
        JButton bk = new JButton("\u2190");
        bk.setFont(new Font("SansSerif", Font.BOLD, 18));
        bk.setForeground(new Color(180, 120, 100));
        bk.setBackground(new Color(0, 0, 0, 0));
        bk.setOpaque(false);
        bk.setBorderPainted(false);
        bk.setContentAreaFilled(false);
        bk.setFocusPainted(false);
        bk.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bk.setPreferredSize(new Dimension(50, 68));
        bk.addActionListener(e -> navigate(backTo));
        bk.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                bk.setForeground(new Color(140, 76, 45));
            }

            public void mouseExited(MouseEvent e) {
                bk.setForeground(new Color(180, 120, 100));
            }
        });
        top.add(bk, BorderLayout.WEST);

        // Center: animal icon + name + status
        JPanel centerInfo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                // transparent
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(200, 68);
            }
        };
        centerInfo.setOpaque(false);
        centerInfo.setLayout(null);

        // g.drawImage(ImageTalk, x, y, imgW, imgH, this);

        p.add(top, BorderLayout.NORTH);

        // ─── Chat area with warm background ───
        JPanel chatArea = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setPaint(
                        new GradientPaint(0, 0, new Color(255, 252, 248), 0, getHeight(), new Color(255, 243, 235)));
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        chatArea.setLayout(new BoxLayout(chatArea, BoxLayout.Y_AXIS));
        chatArea.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        // Initial greeting
        String greeting = sup
                ? "I'm here for you. You can share anything, or we can just sit together quietly. \u2764"
                : "Hey there! What's on your mind today? I'd love to hear from you \u2728";
        addWarmBubble(chatArea, greeting, false, accent);

        JScrollPane cs = new JScrollPane(chatArea);
        cs.setBorder(null);
        cs.getViewport().setOpaque(false);
        cs.setOpaque(false);
        cs.getVerticalScrollBar().setUnitIncrement(16);
        // Hide scrollbar track but keep functionality
        cs.getVerticalScrollBar().setPreferredSize(new Dimension(6, 0));
        p.add(cs, BorderLayout.CENTER);

        // ─── Input bar (warm, rounded, beautiful) ───
        JPanel bar = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setPaint(
                        new GradientPaint(0, 0, new Color(255, 245, 238), 0, getHeight(), new Color(255, 240, 230)));
                g2.fillRect(0, 0, getWidth(), getHeight());
                // Top border
                g2.setColor(new Color(230, 200, 185, 60));
                g2.drawLine(0, 0, getWidth(), 0);
            }
        };
        bar.setLayout(new BorderLayout(10, 0));
        bar.setBorder(BorderFactory.createEmptyBorder(12, 16, 12, 16));

        // Rounded text input
        JTextField in = new JTextField() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 24, 24);
                super.paintComponent(g);
            }
        };
        in.setFont(new Font("SansSerif", Font.PLAIN, 14));
        in.setBackground(new Color(255, 255, 255));
        in.setForeground(new Color(100, 70, 60));
        in.setCaretColor(new Color(140, 76, 45));
        in.setOpaque(false);
        in.setBorder(BorderFactory.createCompoundBorder(
                new AbstractBorder() {
                    @Override
                    public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
                        Graphics2D g2 = (Graphics2D) g;
                        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        g2.setColor(new Color(220, 185, 170));
                        g2.setStroke(new BasicStroke(1.2f));
                        g2.drawRoundRect(x, y, w - 1, h - 1, 24, 24);
                    }

                    @Override
                    public Insets getBorderInsets(Component c) {
                        return new Insets(0, 0, 0, 0);
                    }
                },
                BorderFactory.createEmptyBorder(10, 16, 10, 16)));

        // Send button (warm circle)
        JButton send = new JButton() {
            boolean hover = false;
            {
                setPreferredSize(new Dimension(42, 42));
                setContentAreaFilled(false);
                setBorderPainted(false);
                setFocusPainted(false);
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                addMouseListener(new MouseAdapter() {
                    public void mouseEntered(MouseEvent e) {
                        hover = true;
                        repaint();
                    }

                    public void mouseExited(MouseEvent e) {
                        hover = false;
                        repaint();
                    }
                });
            }

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                // Circle background
                Color btnColor = hover ? new Color(180, 100, 80) : new Color(200, 130, 110);
                g2.setColor(btnColor);
                g2.fillOval(1, 1, 40, 40);
                // Arrow
                g2.setColor(Color.WHITE);
                g2.setFont(new Font("SansSerif", Font.BOLD, 18));
                FontMetrics fm = g2.getFontMetrics();
                String arrow = "\u2191";
                g2.drawString(arrow, 21 - fm.stringWidth(arrow) / 2, 26);
                g2.dispose();
            }
        };

        Runnable sendAction = () -> {
            String t2 = in.getText().trim();
            if (t2.isEmpty())
                return;
            addWarmBubble(chatArea, t2, true, accent);
            in.setText("");
            chatArea.revalidate();
            SwingUtilities.invokeLater(() -> {
                JScrollBar sb = cs.getVerticalScrollBar();
                sb.setValue(sb.getMaximum());
            });
            Timer tm = new Timer(600 + (int) (Math.random() * 800), ev -> {
                addWarmBubble(chatArea, reply(t2, sup), false, accent);
                chatArea.revalidate();
                SwingUtilities.invokeLater(() -> {
                    JScrollBar sb = cs.getVerticalScrollBar();
                    sb.setValue(sb.getMaximum());
                });
            });
            tm.setRepeats(false);
            tm.start();
        };
        send.addActionListener(e -> sendAction.run());
        in.addActionListener(e -> sendAction.run());

        bar.add(in, BorderLayout.CENTER);
        bar.add(send, BorderLayout.EAST);
        p.add(bar, BorderLayout.SOUTH);

        return p;
    }

    // ─── Beautiful warm chat bubbles ───
    void addWarmBubble(JPanel area, String text, boolean fromUser, Color accent) {
        JPanel row = new JPanel();
        row.setLayout(new BoxLayout(row, BoxLayout.X_AXIS));
        row.setOpaque(false);
        row.setAlignmentX(Component.LEFT_ALIGNMENT);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 300));

        if (fromUser) {
            row.add(Box.createHorizontalGlue());
        }

        JPanel bubble = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int w = getWidth(), h = getHeight();
                int arc = 20;

                if (fromUser) {
                    // User bubble: warm coral/pink gradient
                    g2.setPaint(new GradientPaint(0, 0, new Color(220, 140, 120), w, h, new Color(200, 120, 105)));
                    g2.fillRoundRect(0, 0, w, h, arc, arc);
                } else {
                    // Friend bubble: soft cream with warm border
                    g2.setColor(new Color(255, 255, 255));
                    g2.fillRoundRect(0, 0, w, h, arc, arc);
                    g2.setColor(new Color(230, 200, 185, 120));
                    g2.setStroke(new BasicStroke(1f));
                    g2.drawRoundRect(0, 0, w - 1, h - 1, arc, arc);
                }
                g2.dispose();
            }

            @Override
            public Dimension getPreferredSize() {
                Dimension d = super.getPreferredSize();
                return new Dimension(Math.min(d.width, 280), d.height);
            }

            @Override
            public Dimension getMaximumSize() {
                return getPreferredSize();
            }
        };
        bubble.setLayout(new BorderLayout());
        bubble.setOpaque(false);

        JLabel label = new JLabel("<html><div style='padding:10px 14px;width:220px;font-size:12px;color:"
                + (fromUser ? "#FFFFFF" : "#6B4A3C")
                + ";'>" + text + "</div></html>");
        label.setOpaque(false);
        bubble.add(label);

        row.add(bubble);

        if (!fromUser) {
            row.add(Box.createHorizontalGlue());
        }

        area.add(row);
        area.add(Box.createVerticalStrut(10));
    }

    void refreshRecipeDetail() {
        cardPanel.remove(2);
        cardPanel.add(createDetailScreen(
                DAILY_RECIPES[todayRecipe][0],
                DAILY_RECIPES[todayRecipe][1],
                DAILY_RECIPES[todayRecipe][2],
                "\u2615",
                ACCENT_ROSE,
                "TODAY'S RECIPE",
                "\u2665  Show me another one"), "recipedetail", 2);
        cardPanel.revalidate();
        cardPanel.repaint();
    }

    void refreshMusicDetail() {
        cardPanel.remove(1);
        cardPanel.add(createDetailScreen(
                DAILY_MUSIC[todayMusic][0],
                DAILY_MUSIC[todayMusic][1],
                DAILY_MUSIC[todayMusic][2],
                "\u266B",
                ACCENT_WARM,
                "TODAY'S MUSIC",
                "\u25B6  Play now"), "musicdetail", 1);
        cardPanel.revalidate();
        cardPanel.repaint();
    }

    String reply(String m, boolean s) {
        String l = m.toLowerCase();

        // 无论哪种模式，都先检测负面情绪
        if (l.contains("sad") || l.contains("cry") || l.contains("hurt"))
            return "I hear you. It's okay to feel this way. I'm right here with you.";
        if (l.contains("scared") || l.contains("afraid"))
            return "It's okay to feel scared. You are safe here.";
        if (l.contains("angry") || l.contains("mad"))
            return "Your anger is valid \u2014 you deserve better.";
        if (l.contains("alone") || l.contains("lonely"))
            return "You are not alone. I'm here, and people care about you.";
        if (l.contains("tired") || l.contains("exhausted"))
            return "Rest is so important. Be gentle with yourself.";
        if (l.contains("help") || l.contains("support"))
            return "I'm here for you. Would you like me to show you some support resources?";
        if (l.contains("thank"))
            return "You don't need to thank me. You deserve kindness.";

        // 正面/中性回复
        if (l.contains("hello") || l.contains("hi") || l.contains("hey"))
            return "Hello! I'm glad you're here. How are you feeling today?";
        if (l.contains("good") || l.contains("happy") || l.contains("great"))
            return "That's wonderful to hear! What made your day bright?";

        // 默认回复
        if (s) {
            String[] r = { "I'm listening. Take your time.", "You are stronger than you realize.",
                    "It's okay to not be okay. I'm here.", "Be gentle with yourself.", "You matter." };
            return r[(int) (Math.random() * r.length)];
        } else {
            String[] r = { "Tell me more, I'm listening.", "I'm here for you, whatever you need.",
                    "What else is on your mind?", "I'm glad you're sharing with me.",
                    "Take your time. No rush." };
            return r[(int) (Math.random() * r.length)];
        }
    }

    // ═══════ UTILITIES ═══════
    void navigate(String s) {
        cardLayout.show(cardPanel, s);
    }

    static Graphics2D setup(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        return g2;
    }

    static void ctr(Graphics2D g, String t, int cx, int y) {
        FontMetrics f = g.getFontMetrics();
        g.drawString(t, cx - f.stringWidth(t) / 2, y);
    }

    static Color alphaColor(Color c, int a) {
        return new Color(c.getRed(), c.getGreen(), c.getBlue(), a);
    }

    static void drawCard(Graphics2D g, int x, int y, int w, int h, boolean hov, Color ac) {
        if (hov) {
            g.setColor(alphaColor(ac, 10));
            g.fillRoundRect(x - 3, y - 3, w + 6, h + 6, 22, 22);
        }
        g.setColor(hov ? alphaColor(ac, 15) : CARD_BG);
        g.fillRoundRect(x, y, w, h, 18, 18);
        g.setColor(alphaColor(ac, hov ? 80 : 25));
        g.setStroke(new BasicStroke(1));
        g.drawRoundRect(x, y, w, h, 18, 18);
    }

    static void cardIcon(Graphics2D g, int x, int y, String ic, Color ac, boolean hov) {
        g.setFont(new Font("SansSerif", Font.PLAIN, 28));
        g.setColor(ac);
        g.drawString(ic, x + 22, y + 48);
    }

    static void cardText(Graphics2D g, int x, int y, String t, String h, Color ac, boolean hov) {
        g.setFont(FONT_CARD_TITLE);
        g.setColor(hov ? ac : TEXT_PRIMARY);
        g.drawString(t, x + 64, y + 33);

        g.setFont(FONT_SMALL);
        g.setColor(new Color(160, 120, 130));
        g.drawString(h, x + 64, y + 53);
    }

    static void drawBack(Graphics2D g, int x, int y, Rectangle b) {
        int bw = 80, bh = 30;
        b.setBounds(x, y, bw, bh);
        g.setColor(new Color(255, 255, 255, 12));
        g.fillRoundRect(x, y, bw, bh, 10, 10);
        g.setColor(CARD_BORDER);
        g.drawRoundRect(x, y, bw, bh, 10, 10);
        g.setFont(new Font("SansSerif", Font.BOLD, 12));
        g.setColor(TEXT_SECONDARY);
        g.drawString("\u2190 Back", x + 14, y + 20);
    }

    void drawAnimal(Graphics2D g, int cx, int ty, float s, boolean happy) {
        if (animals == null || animals.length == 0)
            return;

        Image img = animals[2];

        int imgW = (int) (260 * s);
        int imgH = (int) (200 * s);

        int x = cx - imgW / 2;
        int y = ty - 40;

        g.drawImage(img, x, y, imgW, imgH, this);
    }

    static class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setPaint(new GradientPaint(0, 0, BG_PRIMARY, 0, getHeight(), new Color(226, 132, 112)));
            g2.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception ignored) {
            }
            new ShelterWellnessApp().setVisible(true);
        });
    }
}
