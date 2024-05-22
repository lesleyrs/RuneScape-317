# RuneScape #317 Client

Based on [Moparisthebest's release](https://www.moparisthebest.com/downloads/rs317.rar).

## Client changes:
**Merged upstream**:
- No deprecation or warnings with Java 21 and instead of Applet being replaced by JComponent use Canvas instead:
  - Fixed white line at bottom of window on Linux, fixed WSL2 mouse being offset
- Don't consume tab key with `setFocusTraversalKeysEnabled(false);`, this fixes dead code for title screen navigation
- Allow connecting to local WSL2 servers from Windows with `System.setProperty("java.net.preferIPv6Addresses", "true");` and using an empty string or localhost instead of 127.0.0.1 or 0.0.0.0
- Fix `findcachedir()` home directory path to actually work with `System.getProperty("user.home") + "/"`, still defaults to c:/ drive on Windows

**Quality of life features**:
- Run client without passing arguments for highmem/members
- Enter to login, Escape to go back to main screen, Tab will move to username field instead of login
- Escape closes interfaces
- Ability to use k/m/b for entering amounts, and allow entering over integer maximum
- Left click compass to make the camera face north
- Hold mousewheel to rotate camera
- Scroll mousewheel to scroll interfaces or zoom camera, `cameraZoom` set to `maxZoom` (6) by default which is arbitrary but works well when not increasing object render distance
- ::toggleroofs command, with `hideRoofs` being `true` by default
- Fix transparency overflow, triangles could have lines drawn at their seams at most camera angles in transparent models like bank booths or ghostly
- Full 512px viewport, the client has a "safe rendering size" that it used but resulted in 511px being drawn instead of 512, despite the area being 512. So you were left with a black border on the right side.
- Bilinear map filtering, smooths out jagged edges on the minimap and compass.
- Added F-keys for switching tabs, defaults past f4 are arbitrary but should be good. To use fkeys on most laptops you need to press the `Fn` key in combination with the fkey. This is obviously awful so you have to change it in UEFI/BIOS to have fkeys work by themselves.
- `System.setProperty("sun.java2d.uiScale", "1.0");` is set by default to avoid scaling issues, if you want to let Windows scale it up according to your resolution settings you can do the following: `task manager>java process properties>compatibility tab>high dpi settings>ignore high dpi scaling>let system scale`. You have to do this for both `java.exe` and `javaw.exe` depending on how you load the game.
- Fullscreen (not resizable) which simply doubles uiScale to stay pixel perfect, paints extra space black, hides decorations, and puts frame in center. If you let your system scale the window as explained above then it likely won't work as it won't be able to fit.
- Swapped out maven build system and dependencies for a makefile to avoid jar bloat and simplicity, and runs on Java 8 again.
- Galkon's sound and music system[^2] without the stupid packet changes
- Server IP address is being read from the filename if it is being run as a `.jar`, so you don't need to recompile to change IP.
- Added moparscape/silabsoft-like server IP login field, leave it empty for localhost. It only appears if `disableCRC` is true as you can't use this to set the initial jaggrab or http address. It uses Jagex's RSA key[^1]:
```
Modulus: 7162900525229798032761816791230527296329313291232324290237849263501208207972894053929065636522363163621000728841182238772712427862772219676577293600221789
Private exponent: 4563042879983685819415859508309337987464904274730456483940553788384065737798175536144539635545496149193181089921240252410947054964044522362195913220892133
Public exponent: 58778699976184461502525193738213253649000149147835990136706041084440742975821
```

**TODO**:
- Space to continue dialogue and number keys to select dialogue options - https://rune-server.org/runescape-development/rs2-client/snippets/657143-spacebar-continue-dialogue.html
- Tab to reply to last private message and right click reply
- Shift-drop items - Not sure if this is smart without dropped item value warnings, use `AHK` otherwise https://github.com/AutoHotkey/AutoHotkey
- Midi fade and fix startup midi volume? combine both sound effect systems so the sound delay works for both (look at 400+)

## Usage
- I added a makefile to easily run the client without an IDE, on Windows you can get `make` from here https://github.com/skeeto/w64devkit and then adding its bin directory to the `PATH` environment variable. For editing this way also grab https://github.com/eclipse-jdtls/eclipse.jdt.ls (requires a recent JDK version and an editor with LSP support). Search for `NOTE` and `TODO` for stuff that needs to be looked at.
- You can get caches from here: https://archive.openrs2.org/ https://runewiki.org/archive/. 317 cache is not recommended right now as it's one of the less complete ones in terms of models and will crash the client in Draynor. #318 and #319 are the most complete caches before fullscreen interfaces in #339.[^3] **TODO**: check if 333 cache gives issues with headicons on a correct server, if yes then #332 will be latest compatible cache.
- Dane's client doesn't exactly replicate the original clients behaviour with errors. It has some checks to avoid them when the original would get a T2 error, but also the opposite way around. Test with the unedited 317 client sometimes to make sure your server is compatible. So far I've restored the T2 crash on bank space limit being exceeded with server packet `53`, and cache validating.

[^1]: https://rune-server.org/runescape-development/rs2-server/configuration/700119-jagexs-login-rsa-key-used-revision-186-revision-597-a.html
[^2]: https://rune-server.org/runescape-development/rs2-client/snippets/363314-sounds-music.html
[^3]: https://runewiki.org/archive/cache/packed/missing.txt https://www.youtube.com/watch?v=tZlj694lcxA https://oldschool.runescape.wiki/w/Graphical_updates_(historical) https://runescape.wiki/w/RuneTek#History https://runescape.wiki/w/Build_number https://runescape.wiki/w/Game_updates https://oldschool.runescape.wiki/w/Game_updates https://oldschool.runescape.wiki/w/User:Hlwys/Revisions - RS3 game updates page is missing some news posts compared to OSRS wiki

## Credits
Many code snippets and ideas have been taken from other clients, which in turn has been worked on by many more people:
RuneScape-317, 317refactor, 2006scape, refactored-client-377, moparscape.
