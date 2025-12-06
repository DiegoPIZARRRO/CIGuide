import oracledb
import json
from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
from typing import List, Optional
import os

app = FastAPI()

DB_USER = "CALAMITYDEV"
DB_PASSWORD = "Tu contraseña aqui"
DSN_NAME = "DSN AQUI"
WALLET_DIR = os.path.join(os.getcwd(), "wallet")

# modelos

class Bosses(BaseModel):
    boss_id: int
    boss_name: str
    boss_description: str
    vida: str
    image_url: str
    item_recommended: List[str]
    item_description: List[str]

class Item(BaseModel):
    id: int
    item_name: str
    description: str
    image_url: str

#conexion a la bd

def get_db_connection():
    return oracledb.connect(
        user=DB_USER,
        password=DB_PASSWORD,
        dsn=DSN_NAME,
        config_dir=WALLET_DIR,
        wallet_location=WALLET_DIR,
        wallet_password="Contraseña aqui"
    )

# endpoints

@app.get("/bosses", response_model=List[Bosses])
def get_bosses():
    connection= None
    cursor = None
    try:
        connection = get_db_connection()
        cursor = connection.cursor()
        cursor.execute("""
                       SELECT boss_id, boss_name, boss_description, vida, image_url,
                       item_recommended, item_description FROM bosses
                       """)
        rows = cursor.fetchall()
        bosses_list = []
        for row in rows:
            try:
                rec = json.loads(row[5]) if row[5] else []
                desc = json.loads(row[6]) if row[6] else []
            except json.JSONDecodeError:
                rec = []
                desc = []

            bosses_list.append(Bosses(
                boss_id=row[0], boss_name=row[1], boss_description=row[2], vida=row[3],
                image_url=row[4], item_recommended=rec, item_description=desc
            ))
        return bosses_list
    except Exception as e:
        print(f"Error fetching bosses: {e}")
        raise HTTPException(status_code=500, detail=str(e))
    finally:
        if cursor:
            cursor.close()
        if connection:
            connection.close()

@app.get("/items", response_model=List[Item])
def get_items():
    connetion = None
    cursor = None
    try:
        connetion = get_db_connection()
        cursor = connetion.cursor()
        cursor.execute("""
                       SELECT id, item_name, description, image_url FROM items
                       """)
        rows = cursor.fetchall()
        items_list = [
            Item(id=row[0], item_name=row[1], description=row[2], image_url=row[3])
            for row in rows
        ]
        return items_list
    except Exception as e:
        print(f"Error fetching items: {e}")
        raise HTTPException(status_code=500, detail=str(e))
    finally:
        if cursor:
            cursor.close()
        if connetion:
            connetion.close()
