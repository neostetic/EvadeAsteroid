### EvadeAsteroid
#### About this project
- This project is a _remake_ of old project of mine, that I made for High School (check it out [here](https://github.com/neostetic/java-arcade-game)).
- **Inspiration:** Space Shooter, Bind of Isac
- **Developer:** [@neostetic](https://github.com/neostetic/java-arcade-game) - Jan Poláček
- **Version:** 2.0 `last update: 23.5.2023`
#### End goal
- Your goal is to survive for as long as possible.
- Every second grands you 1 point to your score total.
- There are **crates** you can collect. Each grants you something

| IMAGE                         | NAME         | DESCRIPTION                                                                                                                   |
|-------------------------------|--------------|-------------------------------------------------------------------------------------------------------------------------------|
| ![em_1.png](files%2Fen_1.png) | _ENEMY_      | This is an _enemy_ you have to avoid. Every time you hit this entity, you lose life or shield, depending on your life status. |
| ![pu_1.png](files%2Fpu_1.png) | _ADD_LIFE_   | This create gives you life. If you are already have full health, you will recive nothing.                                     |
| ![pu_3.png](files%2Fpu_2.png) | _ADD_SHIELD_ | This create gives you shield. If you are already have full shields, you will recive nothing.                                  |
| ![pu_4.png](files%2Fpu_4.png) | _ADD_MONEY_  | This create gives you extra points bonus (default 500).                                                                       |
| ![pu_5.png](files%2Fpu_5.png) | _RANDOM_     | This create will give you random effect.                                                                                      |
| ![pu_6.png](files%2Fpu_6.png) | _NULL_       | This create gives you nothing. _Added for balance reasons._                                                                   |

#### Config
- You can freely edit or change **engine** used variables.
- **Config file's path:** `src/cz/polacek/config/Config.class`

| NAME                      | DESCRIPTION                                                                                                  | DEFAULT VALUE                        |
|---------------------------|--------------------------------------------------------------------------------------------------------------|--------------------------------------|
| **TITLE**                 | Sets title on main window.                                                                                   | `EvadeAsteroid 2.0`                  |
| **PREFERRED_FPS**         | Sets preferred FPS for update (please, don't change).                                                        | `60`                                 |
| **PANEL_CONTROLLER**      | Sets controller's icons support for specific controller (`PS`, `PS_COLOR`, `XBOX`, `XBOX_COLOR`, `COMPUTER`). | `PanelController.COMPUTER`           |
| **COLOR_PALETTE**         | Sets already predefined color palette.                                                                       | `ColorPalette.PICO8`                 |
| **WINDOW_RESIZABLE**      | Sets _window resizable_ property.                                                                            | `false`                              |
| **WINDOW_VISIBLE**        | Sets _window visible_ property.                                                                              | `true`                               |
| **SCALE**                 | Sets scaling level.                                                                                          | `3`                                  |
| **TILE_SIZE**             | Sets tile size.                                                                                              | `16`                                 |
| **TILES_HORIZONTAL**      | Sets horizontal tile count.                                                                                  | `20`                                 |
| **TILES_VERTICAL**        | Sets vertical tile count.                                                                                   | `15`                                 |
| **TILE_COMPUTED**         | Sets properly sized tile variable (don't change).                                                            | `TILE_SIZE * SCALE`                  |
| **WINDOW_WIDTH**          | Sets specific window width.                                                                                  | `(TILE_COMPUTED * TILES_HORIZONTAL)` |
| **WINDOW_HEIGHT**         | Sets specific window height.                                                                                 | `(TILE_COMPUTED * TILES_VERTICAL)`   |
| **GRAVITY**               | Sets player's gravity level.                                                                                 | `1.01`                               |
| **PLAYER_SLOWDOWN**       | Sets player's slowdown level.                                                                                | `16`                                 |
| **PLAYER_SPEED**          | Sets player's calculated speed.                                                                              | `(TILE_SIZE / 10) * SCALE`           |
| **PLAYER_MAX_HEALTH**     | Sets player's max health/lifes.                                                                              | `3`                                  |
| **PLAYER_MAX_SHIELD**     | Sets player's max shields.                                                                                   | `5`                                  |
| **PLAYERNAME_MAX_LENGTH** | Sets maximal length name for scoreboard.                                                                     | `6`                                  |
| **ENEMY_MAX_COUNT**       | Sets max enemy count.                                                                                        | `15` (may change)                    |
| **ENEMY_MIN_VELOCITY**    | Sets min enemy velocity level.                                                                               | `.1`                                 |
| **ENEMY_MAX_VELOCITY**    | Sets max enemy velocity level.                                                                               | `1.25`                               |
| **BULLET_INTERVAL**       | Sets bullet separation interval (ms).                                                                        | `500`                                |
| **BULLET_MAX_COUNT**      | Sets max bullets in one time count.                                                                          | `20`                                 |
| **BULLET_SPEED**          | Sets bullet's speed.                                                                                         | `(TILE_SIZE / 5) * SCALE`            |
| **POWERUP_POINTS**        | Sets point for collecting `ADD_MONEY` create.                                                                | `500`                                |
| **RECORD_FILE**           | Sets record's output file.                                                                                   | `records.csv`                        |
| **ALLOWED_CHARACTERS**    | Sets allowed name characters.                                                                                | ...                                  |
| **STRING_FORMATTERS**     | Sets string patterns/replacements.                                                                           | ...                                  |
