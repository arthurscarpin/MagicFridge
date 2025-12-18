# MagicFridge

```bash
# Build Image
docker build -t magicfridge .
```

```bash
# Run Container
docker run --env-file .env -p 8080:8080 magicfridge
```