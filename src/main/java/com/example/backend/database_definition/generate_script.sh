#!/bin/bash
cat \
"scripts/create_schemas.sql" \
"creating_table.sql" \
"scripts/functions.sql" \
"scripts/initialize_roles.sql" \
"scripts/initialize_users.sql" \
"scripts/initialize_exam_types.sql" \
"scripts/initialize_semester_types.sql" \
"scripts/initialize_years.sql" \
"scripts/initialize_subjects.sql" \
> "initialization_script.sql"