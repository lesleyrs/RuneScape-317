# RuneScape #317 Client

Based on [Moparisthebest's release](https://www.moparisthebest.com/downloads/rs317.rar).

## Client changes:
**Merged upstream**:
- No deprecation or warnings with Java 21 and instead of Applet being replaced by JComponent use Canvas instead:
  - Fixed white line at bottom of window on Linux, fixed WSL2 mouse being offset
- Don't consume tab key with `setFocusTraversalKeysEnabled(false);`, this fixes dead code for title screen navigation
- Allow connecting to local WSL2 servers from Windows with `System.setProperty("java.net.preferIPv6Addresses", "true");` and using an empty string or localhost instead of 127.0.0.1 or 0.0.0.0
- Fix `findcachedir()` home directory path to actually work with `System.getProperty("user.home") + "/"`, still defaults to c:/ drive on Windows
- Require JDK 21 to build, this could be lowered but maven warns about dropping 8 already

**Quality of life features**:
- Run client without passing arguments for highmem/members
- Enter to login, Escape to go back to main screen, Tab will move to username field instead of login
- Escape closes interfaces
- Ability to use k/m/b for entering amounts, and allow entering over integer maximum
- Left click compass to make the camera face north
- Hold mousewheel to rotate camera
- Scroll mousewheel to scroll interfaces or zoom camera, `cameraZoom` set to `maxZoom` by default
- ::toggleroofs command, with `hideRoofs` being `true` by default
- Added F-keys for switching tabs, defaults past f4 are arbitrary but should be good
- Galkon's sound and music system without the stupid packet changes
- Added moparscape/silabsoft-like server IP login field, leave it empty for localhost. Uses Jagex's RSA key[^1]:
```
Modulus: 7162900525229798032761816791230527296329313291232324290237849263501208207972894053929065636522363163621000728841182238772712427862772219676577293600221789
Private exponent: 4563042879983685819415859508309337987464904274730456483940553788384065737798175536144539635545496149193181089921240252410947054964044522362195913220892133
Public exponent: 58778699976184461502525193738213253649000149147835990136706041084440742975821
```

**TODO**:
- Space to continue dialogue and number keys to select dialogue options - https://rune-server.org/runescape-development/rs2-client/snippets/657143-spacebar-continue-dialogue.html
- Tab to reply to last private message and right click reply
- Shift-drop items - check `sortMenuOptions`? Not sure if this is smart without dropped item value warnings, use `AHK` otherwise https://github.com/AutoHotkey/AutoHotkey

## Notes
- Added NOTE markers in the source to show how to set the client to it's original state. also added TODO's for things to look at.
- I added a makefile to easily run the client without an IDE, on Windows you can get `make` from here https://github.com/skeeto/w64devkit and then adding bin directory to the `PATH` environment variable. For editing this way also grab https://github.com/eclipse-jdtls/eclipse.jdt.ls (requires a recent JDK version and editor with LSP support).
- You can get caches from here: https://archive.openrs2.org/ https://runewiki.org/archive/. 317 cache is not recommended right now as it's one of the less complete ones in terms of models and will crash the client in Draynor. #318 and #319 are the most complete caches before fullscreen interfaces in #339.[^2] **TODO**: check if 333 cache gives issues with headicons on a correct server, if yes then #332 will be latest compatible cache.
- Blurry text and a small window on Windows is due to resolution scaling having no effect (125% by default). When running the game you should go to: `task manager>java process properties>compatibility tab>high dpi settings>ignore high dpi scaling>let system scale` to fix it. Other ways to get a bigger window is increasing your screen resolution scaling in settings even more or use the built-in magnifier tool if you are on a very high resolution.
- To use fkeys on most laptops you need to press the `Fn` key in combination with the fkey. This is obviously awful so you have to change it in UEFI/BIOS.
- Dane's client doesn't exactly replicate the original clients behaviour with errors. It has some checks to avoid them when the original would get a T2 error, but also the opposite way around. Test with the unedited 317 client sometimes to make sure your server is compatible. So far I've restored the T2 crash on bank space limit being exceeded with server packet `53`.

[^1]: https://rune-server.org/runescape-development/rs2-server/configuration/700119-jagexs-login-rsa-key-used-revision-186-revision-597-a.html
[^2]: https://runewiki.org/archive/cache/packed/missing.txt https://www.youtube.com/watch?v=tZlj694lcxA https://oldschool.runescape.wiki/w/Graphical_updates_(historical) https://runescape.wiki/w/RuneTek#History https://runescape.wiki/w/Build_number https://runescape.wiki/w/Game_updates https://oldschool.runescape.wiki/w/Game_updates https://oldschool.runescape.wiki/w/User:Hlwys/Revisions - RS3 game updates page is missing some news posts compared to OSRS wiki
