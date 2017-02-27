{
  "staging_env_json": {},
  "running_env_json": {},
  "system_env_json": {
    "VCAP_SERVICES": {
      "cleardb": [
        {
          "credentials": {
            "jdbcUrl": "jdbc:mysql://us-cdbr-iron-east-04.cleardb.net/ad_fdd53e720604e7f?user=b39dd8b6f01dfd&password=9d1569ed",
            "uri": "mysql://b39dd8b6f01dfd:9d1569ed@us-cdbr-iron-east-04.cleardb.net:3306/ad_fdd53e720604e7f?reconnect=true",
            "name": "ad_fdd53e720604e7f",
            "hostname": "us-cdbr-iron-east-04.cleardb.net",
            "port": "3306",
            "username": "b39dd8b6f01dfd",
            "password": "9d1569ed"
          },
          "syslog_drain_url": null,
          "volume_mounts": [],
          "label": "cleardb",
          "provider": null,
          "plan": "spark",
          "name": "mysqlnstance",
          "tags": [
            "Cloud Databases",
            "Data Stores",
            "Web-based",
            "Online Backup & Storage",
            "Single Sign-On",
            "Cloud Security and Monitoring",
            "Certified Applications",
            "Developer Tools",
            "Data Store",
            "Development and Test Tools",
            "Buyable",
            "relational",
            "mysql"
          ]
        }
      ]
    }
  },
  "application_env_json": {
    "VCAP_APPLICATION": {
      "cf_api": "https://donotuseapi.run.pivotal.io",
      "limits": {
        "fds": 16384,
        "mem": 512,
        "disk": 1024
      },
      "application_name": "cf-broker-service",
      "application_uris": [
        "cf-broker-service-triangular-sportiness.cfapps.io"
      ],
      "name": "cf-broker-service",
      "space_name": "demo-broker",
      "space_id": "53d5d534-0ed1-45c5-af05-2ebd1713fb7c",
      "uris": [
        "cf-broker-service-triangular-sportiness.cfapps.io"
      ],
      "users": null,
      "application_id": "3998de6d-0a36-453c-93de-bdf2a9b6e528",
      "version": "9cfbfe18-c9c7-40af-b0da-a1defca75c48",
      "application_version": "9cfbfe18-c9c7-40af-b0da-a1defca75c48"
    }
  }
}