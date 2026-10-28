# Beanify: Modrinth uploads

One row per jar. Tick exactly the game versions listed. Environment: **client-side only**.

Confidence:
- **A**: class and method names checked against published mappings, build file follows the official Fabric / NeoForge template.
- **B**: class names checked, but the Forge build setup (Architectury Loom) comes from docs only.
- **C**: Minecraft 26.x. Assumed the 1.21.11 class layout is unchanged; not checked.

## Fabric + Quilt

| Jar | Modrinth loaders | Game versions to tick | Java | Conf. | Notes |
|---|---|---|---|---|---|
| `beanify-fabric-1.16.x-1.0.0.jar` | Fabric + Quilt | 1.16, 1.16.1, 1.16.2, 1.16.3, 1.16.4, 1.16.5 | 8 | A |  |
| `beanify-fabric-1.17.x-1.0.0.jar` | Fabric + Quilt | 1.17, 1.17.1 | 16 | A |  |
| `beanify-fabric-1.18.x-1.0.0.jar` | Fabric + Quilt | 1.18, 1.18.1, 1.18.2 | 17 | A |  |
| `beanify-fabric-1.19.x-1.0.0.jar` | Fabric + Quilt | 1.19, 1.19.1, 1.19.2, 1.19.3, 1.19.4 | 17 | A |  |
| `beanify-fabric-1.20-1.20.2-1.0.0.jar` | Fabric + Quilt | 1.20, 1.20.1, 1.20.2 | 17 | A |  |
| `beanify-fabric-1.20.4-1.20.6-1.0.0.jar` | Fabric + Quilt | 1.20.4, 1.20.5, 1.20.6 | 17 | A | Compiled against 1.20.4 (Java 17 class files) with --release 17 so it also loads on 1.20.4's Java 17; 1.20.5/1.20.6 run it on Java 21. |
| `beanify-fabric-1.21-1.21.1-1.0.0.jar` | Fabric + Quilt | 1.21, 1.21.1 | 21 | A |  |
| `beanify-fabric-1.21.3-1.21.8-1.0.0.jar` | Fabric + Quilt | 1.21.3, 1.21.4, 1.21.5, 1.21.6, 1.21.7, 1.21.8 | 21 | A |  |
| `beanify-fabric-1.21.9-1.21.10-1.0.0.jar` | Fabric + Quilt | 1.21.9, 1.21.10 | 21 | A |  |
| `beanify-fabric-1.21.11-1.0.0.jar` | Fabric + Quilt | 1.21.11 | 21 | A |  |
| `beanify-fabric-26.1.x-1.0.0.jar` | Fabric + Quilt | 26.1, 26.1.1, 26.1.2 | 25 | C | Quilt on 26.x untested |
| `beanify-fabric-26.2-1.0.0.jar` | Fabric + Quilt | 26.2 | 25 | C | Quilt on 26.x untested |
| `beanify-fabric-26.3-1.0.0.jar` | Fabric + Quilt | 26.3 | 25 | C | Quilt on 26.x untested |

## NeoForge

| Jar | Modrinth loaders | Game versions to tick | Java | Conf. | Notes |
|---|---|---|---|---|---|
| `beanify-neoforge-1.21-1.21.1-1.0.0.jar` | NeoForge | 1.21, 1.21.1 | 21 | A |  |
| `beanify-neoforge-1.21.3-1.21.8-1.0.0.jar` | NeoForge | 1.21.3, 1.21.4, 1.21.5, 1.21.6, 1.21.7, 1.21.8 | 21 | A |  |
| `beanify-neoforge-1.21.9-1.21.10-1.0.0.jar` | NeoForge | 1.21.9, 1.21.10 | 21 | A |  |
| `beanify-neoforge-1.21.11-1.0.0.jar` | NeoForge | 1.21.11 | 21 | A |  |
| `beanify-neoforge-26.1.x-1.0.0.jar` | NeoForge | 26.1, 26.1.1, 26.1.2 | 25 | C |  |
| `beanify-neoforge-26.2-1.0.0.jar` | NeoForge | 26.2 | 25 | C |  |
| `beanify-neoforge-26.3-1.0.0.jar` | NeoForge | 26.3 | 25 | C |  |

## Forge

| Jar | Modrinth loaders | Game versions to tick | Java | Conf. | Notes |
|---|---|---|---|---|---|
| `beanify-forge-1.16.x-1.0.0.jar` | Forge | 1.16, 1.16.1, 1.16.2, 1.16.3, 1.16.4, 1.16.5 | 8 | B | Forge may need a Mixin bootstrap on this version; unverified |
| `beanify-forge-1.17.x-1.0.0.jar` | Forge | 1.17, 1.17.1 | 16 | B | Forge may need a Mixin bootstrap on this version; unverified |
| `beanify-forge-1.18.x-1.0.0.jar` | Forge | 1.18, 1.18.1, 1.18.2 | 17 | B |  |
| `beanify-forge-1.19.x-1.0.0.jar` | Forge | 1.19, 1.19.1, 1.19.2, 1.19.3, 1.19.4 | 17 | B |  |
| `beanify-forge-1.20-1.20.1-1.0.0.jar` | Forge | 1.20, 1.20.1 | 17 | B | split from the neighbouring Forge major on purpose |
| `beanify-forge-1.20.2-1.0.0.jar` | Forge | 1.20.2 | 17 | B | split from the neighbouring Forge major on purpose |
| `beanify-forge-1.20.4-1.0.0.jar` | Forge | 1.20.4 | 17 | B | split from the neighbouring Forge major on purpose |
| `beanify-forge-1.20.6-1.0.0.jar` | Forge | 1.20.6 | 21 | B | split from the neighbouring Forge major on purpose |
| `beanify-forge-1.21-1.21.1-1.0.0.jar` | Forge | 1.21, 1.21.1 | 21 | B |  |
| `beanify-forge-1.21.3-1.21.8-1.0.0.jar` | Forge | 1.21.3, 1.21.4, 1.21.5, 1.21.6, 1.21.7, 1.21.8 | 21 | B |  |
| `beanify-forge-1.21.9-1.21.10-1.0.0.jar` | Forge | 1.21.9, 1.21.10 | 21 | B |  |
| `beanify-forge-1.21.11-1.0.0.jar` | Forge | 1.21.11 | 21 | B |  |
| `beanify-forge-26.1.x-1.0.0.jar` | Forge | 26.1, 26.1.1, 26.1.2 | 25 | C |  |
| `beanify-forge-26.2-1.0.0.jar` | Forge | 26.2 | 25 | C |  |
| `beanify-forge-26.3-1.0.0.jar` | Forge | 26.3 | 25 | C |  |
