package FightOrDie.graphics;
import java.awt.Font;
import java.awt.image.BufferedImage;
public class Assets
    {
    private static final int width = 32, height = 32;
    public static BufferedImage player_stand, dirt, grass, stone, tree, skeleton_stand, zombie_stand;
    public static BufferedImage[] player_down, player_up, player_left, player_right, player_UpRight, player_UpLeft, player_DownRight, player_DownLeft;
    public static BufferedImage[] skeleton_down, skeleton_up, skeleton_left, skeleton_right, zombie_down, zombie_up, zombie_left, zombie_right;
    public static BufferedImage[] btn_start, startGame, loadGame;
    public static BufferedImage[] player_head, player_torso, player_legs, enemy_head, enemy_torso, enemy_legs;
    public static BufferedImage inventoryImg, keyImg, backgroundImg;
    public static BufferedImage potionImg, chestImg;
    
    public static Font itemInv;
    
    public static void init()
        {
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));
        SpriteSheet player = new SpriteSheet(ImageLoader.loadImage("/textures/player.png"));
        SpriteSheet enemy = new SpriteSheet(ImageLoader.loadImage("/textures/enemy.png"));
        SpriteSheet fight = new SpriteSheet(ImageLoader.loadImage("/textures/fight.png"));
        SpriteSheet fightButton = new SpriteSheet(ImageLoader.loadImage("/textures/fightButton.png"));
        SpriteSheet chest = new SpriteSheet(ImageLoader.loadImage("/textures/chest.png"));
        SpriteSheet menuButtons = new SpriteSheet(ImageLoader.loadImage("/textures/menuButtons.png"));
        
        btn_start = new BufferedImage[2];
        startGame = new BufferedImage[2];
        loadGame = new BufferedImage[2];
        
        btn_start[0] = fightButton.crop(0, 0, 150, 100);
        btn_start[1] = fightButton.crop(0, 100, 150, 100);
        
        startGame[0] = menuButtons.crop(0, 0, 200, 125);
        startGame[1] = menuButtons.crop(0, 125, 200, 125);
        loadGame[0] = menuButtons.crop(200, 0, 200, 125);
        loadGame[1] = menuButtons.crop(200, 125, 200, 125);
        
        
        player_head = new BufferedImage[2];
        player_torso = new BufferedImage[2];
        player_legs = new BufferedImage[2];
        enemy_head = new BufferedImage[2];
        enemy_torso = new BufferedImage[2];
        enemy_legs = new BufferedImage[2];
        
        player_head[0] = 	fight.crop(0, 0, 135, 135);
        player_head[1] = 	fight.crop(1, 1, 135, 135);
        enemy_head[0] = 	fight.crop(135, 0, 135, 135);
        enemy_head[1] = 	fight.crop(134, 1, 135, 135);
        
        player_torso[0] = 	fight.crop(447, 0, 447, 447);
        player_torso[1] = 	fight.crop(448, 1, 447, 447);
        enemy_torso[0] = 	fight.crop(0, 135, 447, 447);
        enemy_torso[1] = 	fight.crop(1, 136, 447, 447);
        
        player_legs[0] = 	fight.crop(1182, 0, 289, 336);
        player_legs[1] = 	fight.crop(1181, 1, 289, 336);
        enemy_legs[0] = 	fight.crop(894, 0, 289, 336);
        enemy_legs[1] = 	fight.crop(895, 1, 289, 336);
        
        player_down = new BufferedImage[3];
        player_up = new BufferedImage[3];
        player_left = new BufferedImage[3];
        player_right = new BufferedImage[3];
        player_UpRight = new BufferedImage[3];
        player_UpLeft = new BufferedImage[3];
        player_DownRight = new BufferedImage[3];
        player_DownLeft = new BufferedImage[3];
        player_stand = player.crop(5 * width, 5 * height, width, height);
        
        skeleton_down = new BufferedImage[3];
        skeleton_up = new BufferedImage[3];
        skeleton_left = new BufferedImage[3];
        skeleton_right = new BufferedImage[3];
        
        zombie_down = new BufferedImage[3];
        zombie_up = new BufferedImage[3];
        zombie_left = new BufferedImage[3];
        zombie_right = new BufferedImage[3];
        
        player_down[0] = 	player.crop(0 * width, 0 * height, width, height);
        player_down[1] = 	player.crop(1 * width, 0 * height, width, height);
        player_down[2] = 	player.crop(2 * width, 0 * height, width, height);
        player_left[0] = 	player.crop(0 * width, 1 * height, width, height);
        player_left[1] = 	player.crop(1 * width, 1 * height, width, height);
        player_left[2] = 	player.crop(2 * width, 1 * height, width, height);
        player_right[0] = 	player.crop(0 * width, 2 * height, width, height);
        player_right[1] = 	player.crop(1 * width, 2 * height, width, height);
        player_right[2] = 	player.crop(2 * width, 2 * height, width, height);
        player_up[0] = 		player.crop(0 * width, 3 * height, width, height);
        player_up[1] = 		player.crop(1 * width, 3 * height, width, height);
        player_up[2] = 		player.crop(2 * width, 3 * height, width, height);
        
        player_DownLeft[0] = 	player.crop(3 * width, 0 * height, width, height);
        player_DownLeft[1] = 	player.crop(4 * width, 0 * height, width, height);
        player_DownLeft[2] = 	player.crop(5 * width, 0 * height, width, height);
        player_UpLeft[0] = 		player.crop(3 * width, 1 * height, width, height);
        player_UpLeft[1] = 		player.crop(4 * width, 1 * height, width, height);
        player_UpLeft[2] = 		player.crop(5 * width, 1 * height, width, height);
        player_DownRight[0] = 	player.crop(3 * width, 2 * height, width, height);
        player_DownRight[1] = 	player.crop(4 * width, 2 * height, width, height);
        player_DownRight[2] = 	player.crop(5 * width, 2 * height, width, height);
        player_UpRight[0] = 	player.crop(3 * width, 3 * height, width, height);
        player_UpRight[1] = 	player.crop(4 * width, 3 * height, width, height);
        player_UpRight[2] = 	player.crop(5 * width, 3 * height, width, height);
        
        skeleton_down[0] = 	enemy.crop(3 * width, 0 * height, width, 2 * height);
        skeleton_down[1] = 	enemy.crop(4 * width, 0 * height, width, 2 * height);
        skeleton_down[2] = 	enemy.crop(5 * width, 0 * height, width, 2 * height);
        skeleton_left[0] = 	enemy.crop(3 * width, 2 * height, width, 2 * height);
        skeleton_left[1] = 	enemy.crop(4 * width, 2 * height, width, 2 * height);
        skeleton_left[2] = 	enemy.crop(5 * width, 2 * height, width, 2 * height);
        skeleton_right[0] = 	enemy.crop(3 * width, 4 * height, width, 2 * height);
        skeleton_right[1] = 	enemy.crop(4 * width, 4 * height, width, 2 * height);
        skeleton_right[2] = 	enemy.crop(5 * width, 4 * height, width, 2 * height);
        skeleton_up[0] = 		enemy.crop(3 * width, 6 * height, width, 2 * height);
        skeleton_up[1] = 		enemy.crop(4 * width, 6 * height, width, 2 * height);
        skeleton_up[2] = 		enemy.crop(5 * width, 6 * height, width, 2 * height);
        skeleton_stand = enemy.crop(4 * width, 0 * height, width, 2 * height);
        
        zombie_down[0] = 	enemy.crop(0 * width, 0 * height, width, 2 * height);
        zombie_down[1] = 	enemy.crop(1 * width, 0 * height, width, 2 * height);
        zombie_down[2] = 	enemy.crop(2 * width, 0 * height, width, 2 * height);
        zombie_left[0] = 	enemy.crop(0 * width, 2 * height, width, 2 * height);
        zombie_left[1] = 	enemy.crop(1 * width, 2 * height, width, 2 * height);
        zombie_left[2] = 	enemy.crop(2 * width, 2 * height, width, 2 * height);
        zombie_right[0] = 	enemy.crop(0 * width, 4 * height, width, 2 * height);
        zombie_right[1] = 	enemy.crop(1 * width, 4 * height, width, 2 * height);
        zombie_right[2] = 	enemy.crop(2 * width, 4 * height, width, 2 * height);
        zombie_up[0] = 		enemy.crop(0 * width, 6 * height, width, 2 * height);
        zombie_up[1] = 		enemy.crop(1 * width, 6 * height, width, 2 * height);
        zombie_up[2] = 		enemy.crop(2 * width, 6 * height, width, 2 * height);
        zombie_stand = enemy.crop(1 * width, 0 * height, width, 2 * height);
        
        tree = sheet.crop(0, 0, width, height);
        dirt = sheet.crop(width, 0, width, height);
        grass = sheet.crop(2 * width, 0, width, height);
        stone = sheet.crop(3 * width, 0, width, height);
        
        chestImg = chest.crop(290, 0, 50, 50);
        
        inventoryImg = ImageLoader.loadImage("/textures/inventoryScreen.png");
        potionImg = ImageLoader.loadImage("/textures/potions_1.png");
        keyImg = ImageLoader.loadImage("/textures/key.png");
        backgroundImg = ImageLoader.loadImage("/textures/menuBackground.jpg");
        
        itemInv = FontLoader.loadFont("res/fonts/KARNIVOL.ttf", 15);
        }
    }