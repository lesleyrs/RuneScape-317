# RuneScape #317 Client

Based on [Moparisthebest's release](https://www.moparisthebest.com/downloads/rs317.rar).

## Quality of life features added:
- No deprecation or warnings, tab key fixed by disabling JComponents focus traversal keys
- Allow connecting to local WSL2 servers from Windows by preferring IPv6 and using localhost
- Fix findcachedir home directory path to actually work, still defaults to C:/ on Windows
- Added moparscape/silabsoft-like server IP login field, uses original Jagex RSA keys by default: https://rune-server.org/runescape-development/rs2-server/configuration/700119-jagexs-login-rsa-key-used-revision-186-revision-597-a.html
- Escape closes interfaces
- Hold mousewheel to rotate camera
- Ability to use k/m/b for entering amounts, and allow entering over integer max
- Enter to login, Escape to go back to main screen
- Left click compass to make the camera reset/face north

More to come...

## Issues (inherited from Danes client)
- Swapping out deprecated Applet brings some issues with it:
  - On Linux there is a white line at bottom of the window
  - WSL2 gui suffers from mouse position being offset
