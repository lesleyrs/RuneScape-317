public final class PacketIn {

    // Names based on https://www.rune-server.ee/runescape-development/rs-503-client-and-server/informative-threads/622541-great-nxt-beta-dump-thread.html
    public static final int[] SIZE = new int[256];

    public static final int CAM_LOOKAT = 177;
    public static final int CAM_RESET = 107;
    public static final int CAM_SETPOS = 166;
    public static final int CAM_SHAKE = 35;
    public static final int IF_CHAT = 164;
    public static final int IF_CHAT_STICKY = 218;
    public static final int CHAT_FILTER_SETTINGS = 206;
    public static final int CLEAR_MAP_FLAG = 78;
    public static final int FRIENDLIST_LOADED = 221;
    public static final int FRIEND_STATUS = 50;
    public static final int HINT_ARROW = 254;
    public static final int IF_CLOSE = 219;
    public static final int IF_SETANGLE = 230;
    public static final int IF_SETANIM = 200;
    public static final int IF_SETCOLOR = 122;
    public static final int IF_SETHIDE = 171;
    public static final int IF_SETMODEL = 8;
    public static final int IF_SETNPCHEAD = 75;
    public static final int IF_SETOBJECT = 246;
    public static final int IF_SETPLAYERHEAD = 185;
    public static final int IF_SETPOSITION = 70;
    public static final int IF_SETSCROLLPOS = 79;
    public static final int IF_SETTEXT = 126;
    public static final int IF_STOPANIM = 142;
    public static final int IF_VIEWPORT = 97;
    public static final int IF_VIEWPORT_AND_SIDEBAR = 248;
    public static final int IF_VIEWPORT_OVERLAY = 208;
    public static final int IGNORE_LIST = 214;
    public static final int INPUT_AMOUNT = 27;
    public static final int INPUT_NAME = 187;
    public static final int INV_CLEAR = 72;
    public static final int LAST_LOGIN_INFO = 176;
    public static final int LOCAL_PLAYER = 249;
    public static final int LOC_ADD = 151;
    public static final int LOC_CHANGE = 160;
    public static final int LOC_DEL = 101;
    public static final int LOGOUT = 109;
    public static final int MESSAGE_GAME = 253;
    public static final int MESSAGE_PUBLIC = 196;
    public static final int MIDI_JINGLE = 121;
    public static final int MIDI_SONG = 74;
    public static final int MINIMAP_TOGGLE = 99;
    public static final int MULTIZONE = 61;
    public static final int OBJ_ADD = 44;
    public static final int OBJ_COUNT = 84;
    public static final int OBJ_DEL = 156;
    public static final int OBJ_REVEAL = 215;
    public static final int LOC_PLAYER = 147;
    public static final int MAP_PROJECTILE = 117;
    public static final int REBUILD_REGION = 73;
    public static final int REBUILD_REGION_INSTANCE = 241;
    public static final int RESET_ANIMS = 1;
    public static final int RESET_CLIENT_VARCACHE = 68;
    public static final int SET_PLAYER_OP = 104;
    public static final int MAP_SOUND = 105;
    public static final int MAP_ANIM = 4;
    public static final int SYNC_NPCS = 65;
    public static final int SYNC_PLAYERS = 81;
    public static final int SYNTH_SOUND = 174;
    public static final int IF_TAB = 71;
    public static final int TAB_HINT = 24;
    public static final int TAB_SELECTED = 106;
    public static final int UPDATE_INV_FULL = 53;
    public static final int UPDATE_INV_PARTIAL = 34;
    public static final int UPDATE_REBOOT_TIMER = 114;
    public static final int UPDATE_RUNENERGY = 110;
    public static final int UPDATE_RUNWEIGHT = 240;
    public static final int UPDATE_STAT = 134;
    public static final int VARP_LARGE = 87;
    public static final int VARP_SMALL = 36;
    public static final int ZONE_BASE = 85;
    public static final int ZONE_CLEAR = 64;
    public static final int ZONE_UPDATE = 60;

    static {
        SIZE[MAP_ANIM] = 6;
        SIZE[IF_SETMODEL] = 4;
        SIZE[TAB_HINT] = 1;
        SIZE[UPDATE_INV_PARTIAL] = -2;
        SIZE[CAM_SHAKE] = 4;
        SIZE[VARP_SMALL] = 3;
        SIZE[OBJ_ADD] = 5;
        SIZE[47] = 6;
        SIZE[FRIEND_STATUS] = 9;
        SIZE[UPDATE_INV_FULL] = -2;
        SIZE[ZONE_UPDATE] = -2;
        SIZE[MULTIZONE] = 1;
        SIZE[ZONE_CLEAR] = 2;
        SIZE[SYNC_NPCS] = -2;
        SIZE[IF_SETPOSITION] = 6;
        SIZE[IF_TAB] = 3;
        SIZE[INV_CLEAR] = 2;
        SIZE[REBUILD_REGION] = 4;
        SIZE[MIDI_SONG] = 2;
        SIZE[IF_SETNPCHEAD] = 4;
        SIZE[IF_SETSCROLLPOS] = 4;
        SIZE[SYNC_PLAYERS] = -2;
        SIZE[OBJ_COUNT] = 7;
        SIZE[ZONE_BASE] = 2;
        SIZE[VARP_LARGE] = 6;
        SIZE[IF_VIEWPORT] = 2;
        SIZE[MINIMAP_TOGGLE] = 1;
        SIZE[LOC_DEL] = 2;
        SIZE[SET_PLAYER_OP] = -1;
        SIZE[MAP_SOUND] = 4;
        SIZE[TAB_SELECTED] = 1;
        SIZE[UPDATE_RUNENERGY] = 1;
        SIZE[UPDATE_REBOOT_TIMER] = 2;
        SIZE[MAP_PROJECTILE] = 15;
        SIZE[MIDI_JINGLE] = 4;
        SIZE[IF_SETCOLOR] = 4;
        SIZE[IF_SETTEXT] = -2;
        SIZE[UPDATE_STAT] = 6;
        SIZE[IF_STOPANIM] = 2;
        SIZE[LOC_PLAYER] = 14;
        SIZE[LOC_ADD] = 4;
        SIZE[OBJ_DEL] = 3;
        SIZE[LOC_CHANGE] = 4;
        SIZE[IF_CHAT] = 2;
        SIZE[CAM_SETPOS] = 6;
        SIZE[IF_SETHIDE] = 3;
        SIZE[SYNTH_SOUND] = 5;
        SIZE[LAST_LOGIN_INFO] = 10;
        SIZE[CAM_LOOKAT] = 6;
        SIZE[IF_SETPLAYERHEAD] = 2;
        SIZE[MESSAGE_PUBLIC] = -1;
        SIZE[IF_SETANIM] = 4;
        SIZE[CHAT_FILTER_SETTINGS] = 3;
        SIZE[IF_VIEWPORT_OVERLAY] = 2;
        SIZE[IGNORE_LIST] = -2;
        SIZE[OBJ_REVEAL] = 7;
        SIZE[IF_CHAT_STICKY] = 2;
        SIZE[FRIENDLIST_LOADED] = 1;
        SIZE[IF_SETANGLE] = 8;
        SIZE[UPDATE_RUNWEIGHT] = 2;
        SIZE[REBUILD_REGION_INSTANCE] = -2;
        SIZE[IF_SETOBJECT] = 6;
        SIZE[IF_VIEWPORT_AND_SIDEBAR] = 4;
        SIZE[LOCAL_PLAYER] = 3;
        SIZE[MESSAGE_GAME] = -1;
        SIZE[HINT_ARROW] = 6;
    }

    public static final int[] packetSizes = {
        0, 0, 0, 0, 6, 0, 0, 0, 4, 0, // 0
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 10
        0, 0, 0, 0, 1, 0, 0, 0, 0, 0, // 20
        0, 0, 0, 0, -2, 4, 3, 0, 0, 0, // 30
        0, 0, 0, 0, 5, 0, 0, 6, 0, 0, // 40
        9, 0, 0, -2, 0, 0, 0, 0, 0, 0, // 50
        -2, 1, 0, 0, 2, -2, 0, 0, 0, 0, // 60
        6, 3, 2, 4, 2, 4, 0, 0, 0, 4, // 70
        0, -2, 0, 0, 7, 2, 0, 6, 0, 0, // 80
        0, 0, 0, 0, 0, 0, 0, 2, 0, 1, // 90
        0, 2, 0, 0, -1, 4, 1, 0, 0, 0, // 100
        1, 0, 0, 0, 2, 0, 0, 15, 0, 0, // 110
        0, 4, 4, 0, 0, 0, -2, 0, 0, 0, // 120
        0, 0, 0, 0, 6, 0, 0, 0, 0, 0, // 130
        0, 0, 2, 0, 0, 0, 0, 14, 0, 0, // 140
        0, 4, 0, 0, 0, 0, 3, 0, 0, 0, // 150
        4, 0, 0, 0, 2, 0, 6, 0, 0, 0, // 160
        0, 3, 0, 0, 5, 0, 10, 6, 0, 0, // 170
        0, 0, 0, 0, 0, 2, 0, 0, 0, 0, // 180
        0, 0, 0, 0, 0, 0, -1, 0, 0, 0, // 190
        4, 0, 0, 0, 0, 0, 3, 0, 2, 0, // 200
        0, 0, 0, 0, -2, 7, 0, 0, 2, 0, // 210
        0, 1, 0, 0, 0, 0, 0, 0, 0, 0, // 220
        8, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 230
        2, -2, 0, 0, 0, 0, 6, 0, 4, 3, // 240
        0, 0, 0, -1, 6, 0, 0 // 250
    };

    private PacketIn() {

    }
}
