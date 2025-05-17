# PuffStatusPlugin

PuffStatusPlugin is a Minecraft Paper plugin that lets players set a custom colored status, which appears before their name in chat and the tab list. Statuses support rich formatting using the [MiniMessage](https://docs.advntr.dev/minimessage/format.html) syntax from the Adventure API.

## Features

- Players can set their own status with `/status <your status message>`
- Statuses are shown before the player's name in chat and the tab list
- Supports MiniMessage formatting for colors, gradients, and decorations
- Statuses are saved and persist across server restarts

## Usage

- Use `/status <your status message>` to set your status. Example:
  - `/status <red>AFK`
  - `/status <gradient:blue:green>Chilling`
  - `/status <bold><gold>King`
- Your status will appear before your name in chat and the tab list.

## Formatting Reference

- PuffStatusPlugin uses [MiniMessage](https://docs.advntr.dev/minimessage/format.html) for formatting.
- See the [MiniMessage Format Reference](https://docs.advntr.dev/minimessage/format.html) for a full list of supported tags, including:
  - [Colors](https://docs.advntr.dev/minimessage/format.html#colors)
  - [Gradients](https://docs.advntr.dev/minimessage/format.html#gradients)
  - [Decorations (bold, italic, etc.)](https://docs.advntr.dev/minimessage/format.html#decorations)
  - [Resetting formatting](https://docs.advntr.dev/minimessage/format.html#reset)

## Example Statuses

- `<red>AFK`
- `<bold><blue>Builder`
- `<gradient:yellow:green>Online`
- `<italic><gray>Busy`

## Requirements

- Minecraft server running [Paper](https://papermc.io/)
- Java 17 or newer

## License

This project is licensed under the [Apache License, Version 2.0](https://www.apache.org/licenses/LICENSE-2.0).