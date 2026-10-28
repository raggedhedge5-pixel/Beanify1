#!/usr/bin/env python3
"""Regenerate MODRINTH_UPLOADS.md from build-matrix.json (run from the repo root: python3 tools/make_listing.py)."""
import json
import pathlib

root = pathlib.Path(__file__).resolve().parent.parent
matrix = json.loads((root / "build-matrix.json").read_text())
VERSION = "1.0.0"

LOADER_LABEL = {
    "fabric": "Fabric + Quilt",
    "neoforge": "NeoForge",
    "forge": "Forge",
}


def tier(t):
    if t["minecraft_version"].startswith("26."):
        return "C"
    if t["project"] == "forge":
        return "B"
    return "A"


def loaders_for(t):
    base = t["project"].split("-")[0]  # fabric-remap / fabric-unobf -> fabric
    return LOADER_LABEL[base]


def jar_for(t):
    base = t["project"].split("-")[0]
    return f"beanify-{base}-{t['range_label']}-{VERSION}.jar"


def notes(t):
    out = []
    if t["id"] in ("forge-1.16", "forge-1.17"):
        out.append("Forge may need a Mixin bootstrap on this version; unverified")
    if t["id"] in ("forge-1.20.1", "forge-1.20.2", "forge-1.20.4", "forge-1.20.6"):
        out.append("split from the neighbouring Forge major on purpose")
    if "quilt_note" in t:
        out.append("Quilt on 26.x untested")
    if t.get("note"):
        out.append(t["note"])
    return "; ".join(out)


lines = [
    "# Beanify: Modrinth uploads",
    "",
    "One row per jar. Tick exactly the game versions listed. Environment: **client-side only**.",
    "",
    "Confidence:",
    "- **A**: class and method names checked against published mappings, build file follows the official Fabric / NeoForge template.",
    "- **B**: class names checked, but the Forge build setup (Architectury Loom) comes from docs only.",
    "- **C**: Minecraft 26.x. Assumed the 1.21.11 class layout is unchanged; not checked.",
    "",
]
for loader_key in ("fabric", "neoforge", "forge"):
    rows = [t for t in matrix["targets"] if t["project"].split("-")[0] == loader_key]
    lines += [f"## {LOADER_LABEL[loader_key]}", "", "| Jar | Modrinth loaders | Game versions to tick | Java | Conf. | Notes |", "|---|---|---|---|---|---|"]
    for t in rows:
        lines.append(
            f"| `{jar_for(t)}` | {loaders_for(t)} | {', '.join(t['game_versions'])} | {t['java']} | {tier(t)} | {notes(t)} |"
        )
    lines.append("")

(root / "MODRINTH_UPLOADS.md").write_text("\n".join(lines))
print(f"wrote MODRINTH_UPLOADS.md with {len(matrix['targets'])} rows")
