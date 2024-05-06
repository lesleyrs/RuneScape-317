# RuneScape #317 Client

Based on [Moparisthebest's release](https://www.moparisthebest.com/downloads/rs317.rar).

## Quality of life features added:
- No deprecation or warnings
- Allow connecting to local WSL2 servers from Windows by preferring IPv6 and using localhost instead of 127.0.0.1
- Fix findcachedir home directory path to actually work, still defaults to `c:/` drive on Windows
- Added moparscape/silabsoft-like server IP login field, uses original Jagex RSA keys by default: https://rune-server.org/runescape-development/rs2-server/configuration/700119-jagexs-login-rsa-key-used-revision-186-revision-597-a.html, leave server IP empty for localhost
- Escape closes interfaces
- Hold mousewheel to rotate camera
- Ability to use k/m/b for entering amounts, and allow entering over integer max
- Enter to login, Escape to go back to main screen
- Left click compass to make the camera reset/face north

I'm trying to keep this as close to the original client as possible by only adding simple QOL.
