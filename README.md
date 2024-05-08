# RuneScape #317 Client

Based on [Moparisthebest's release](https://www.moparisthebest.com/downloads/rs317.rar).

## Client changes:
**Merged upstream**:
- No deprecation or warnings and fixed new window related issues after Applet got replaced:
  - Fixed white line at bottom of window on Linux, fixed WSL2 mouse offset, don't consume tab key by disabling focus traversal keys
- Allow connecting to local WSL2 servers from Windows by preferring IPv6 and using an empty string or localhost instead of 127.0.0.1 or 0.0.0.0
- Fix findcachedir home directory path to actually work, still defaults to c:/ drive on Windows

**Quality of life features**:
- Escape closes interfaces
- Hold mousewheel to rotate camera
- Added camera zoom with either mousewheel scroll or numpad PgUp/PgDn, max zoom-in is the original default **TODO**: increase viewing distance based on cam zoom + toggleroof cmd?
- Ability to use k/m/b for entering amounts, and allow entering over integer max
- Enter to login, Escape to go back to main screen, Tab unlike Enter won't login when reaching last login field
- Left click compass to make the camera face north
- Added fkeys for switching tabs, defaults past f4 are arbitrary but should be good
- Added moparscape/silabsoft-like server IP login field, leave it empty for localhost. Uses original Jagex RSA keys[^1]:
```
Modulus: 7162900525229798032761816791230527296329313291232324290237849263501208207972894053929065636522363163621000728841182238772712427862772219676577293600221789
Private exponent: 4563042879983685819415859508309337987464904274730456483940553788384065737798175536144539635545496149193181089921240252410947054964044522362195913220892133
Public exponent: 58778699976184461502525193738213253649000149147835990136706041084440742975821
```

## Notes
- I added a makefile to easily run the client without an IDE, on Windows you can get `make` from here by adding the bin directory to `PATH` environment variable https://github.com/skeeto/w64devkit. For editing this way also grab https://github.com/eclipse-jdtls/eclipse.jdt.ls (requires a recent JDK version). I'm trying to keep this close to the original client by only adding simple QOL.
- You can get caches from here: https://archive.openrs2.org/ https://runewiki.org/archive/. 317 cache is not recommended right now as it's one of the less complete ones in terms of models and will crash the client in Draynor. #318 and #319 are the most complete pre-fullscreen interfaces[^2] but any other revision before #339? works.[^3]
- Blurry text and a small window on Windows is due to resolution scaling having no effect (125% by default). When running the game you should go to: `task manager>java process properties>compatibility tab>high dpi settings>ignore high dpi scaling>let system scale` to fix it. Other ways to get a bigger window is increasing your screen resolution scaling in settings even more or use the built-in magnifier tool if you are on a very high resolution.
- To use fkeys on most laptops you need to press the `Fn` key in combination with the fkey. This is obviously awful so you have to change it in UEFI/BIOS.

[^1]: https://rune-server.org/runescape-development/rs2-server/configuration/700119-jagexs-login-rsa-key-used-revision-186-revision-597-a.html
[^2]: https://runewiki.org/archive/cache/packed/missing.txt
[^3]: https://www.youtube.com/watch?v=tZlj694lcxA https://oldschool.runescape.wiki/w/Graphical_updates_(historical) https://runescape.wiki/w/RuneTek#History https://runescape.wiki/w/Build_number https://runescape.wiki/w/Game_updates https://oldschool.runescape.wiki/w/Game_updates https://oldschool.runescape.wiki/w/User:Hlwys/Revisions - RS3 game updates page is missing some news posts compared to OSRS wiki
