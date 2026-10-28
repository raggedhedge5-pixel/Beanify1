# Beanify

A client-side visual mod. Every player becomes a bean: **no arms, no sleeves, legs that never swing**. Held items still render where a hand would be, and chestplates lose their armor arms too.

Loaders: **Fabric, Quilt, Forge, NeoForge**. Minecraft **1.16.5 to 26.3** (see `MODRINTH_UPLOADS.md` for the exact jar list).

## How it works

Two small Mixins, no Fabric API and no config.

- `PlayerModel.setupAnim` (tail): `leftArm`, `rightArm`, `leftSleeve`, `rightSleeve` get `visible = false`; both legs get their rotations zeroed; pants overlays are re-copied from the legs.
- Armor: before 1.21.2 armor models copy the player's pose, so frozen legs carry over and `HumanoidArmorLayer.setPartVisibility` hides armor arms. From 1.21.2 armor is posed from the render state, so `HumanoidModel.setupAnim` is hooked too (players only; zombies, skeletons and armor stands keep their arms).
- Arm **rotation** is never touched. Held items are positioned from the arm part's transform, which ignores `visible`, so items stay in the right place.

Every injector uses `require = 0` and the mixin config is `"required": false`, so if a Minecraft update renames something the mod does nothing instead of crashing.

## Honest note on FPS

This is a look change, not an optimization. The animation maths runs before the Mixin and is trivial anyway, and the only saving is skipping a few arm cubes per player. Expect no measurable FPS difference.

## Building

The sandbox this was written in cannot reach Mojang, Fabric, Forge or Maven Central, so **nothing here has been compiled against real Minecraft**. Use GitHub Actions as the first real build:

1. Create a GitHub repo and push this folder.
2. Actions tab, **Build all jars**, **Run workflow**. Each target is its own job and one failure does not stop the others.
3. Download the `beanify-<target>` artifacts and upload them to Modrinth following `MODRINTH_UPLOADS.md`.

Local build of one target (needs JDK 21, or 25 for 26.x): `cd fabric-remap && gradle build -PbeanTarget=fabric-1.20.2`. All targets are defined in `build-matrix.json`; `python3 tools/make_listing.py` regenerates `MODRINTH_UPLOADS.md` after you edit it.

## Layout

| Folder | What it is |
|---|---|
| `common/entity` | Mixins for 1.16.5 to 1.21.1 |
| `common/state` | Mixins for 1.21.2 to 1.21.8 (render states, `PlayerRenderState`) |
| `common/avatar-old-pkg` | Mixins for 1.21.9 to 1.21.10 (`AvatarRenderState`, `PlayerModel` in `client.model`) |
| `common/avatar-player-pkg` | Mixins for 1.21.11 and 26.x (`PlayerModel` in `client.model.player`) |
| `fabric-remap` | Fabric/Quilt build for 1.16.5 to 1.21.11 (`net.fabricmc.fabric-loom-remap`) |
| `fabric-unobf` | Fabric/Quilt build for 26.x (`net.fabricmc.fabric-loom`, no mappings) |
| `neoforge` | NeoForge build (ModDevGradle `net.neoforged.moddev`) |
| `forge` | Forge build (Architectury Loom, `loom.platform=forge`) |

## Known risks, most likely first

1. **Forge** is the least verified: the Architectury Loom settings come from its docs, not a working build. Forge 26.x is the shakiest (whether Loom wants a `mappings` line there is unknown).
2. **Forge 1.16.5 and 1.17.1** may need a separate Mixin bootstrap mod to load Mixin configs at all.
3. **26.x** assumes `PlayerModel`, `HumanoidModel.setupAnim(HumanoidRenderState)` and `AvatarRenderState` match 1.21.11. NeoForge's 26.3 notes only mention `HumanoidModel` gaining `setupSwimAnimation`, which does not affect us.
4. **Quilt on 26.x**: Quilt's meta server lists the versions, but running an unobfuscated Fabric-style jar there is untested.
5. If a Forge pack needs a `pack.mcmeta`, add one; none is included because the right `pack_format` differs per version.
