db.createUser(
        {
            user: "alan",
            pwd: "Passw0rd",
            roles: [
                {
                    role: "readWrite",
                    db: "transactions"
                }
            ]
        }
);