SELECT
DISTINCT node.nid AS nid,
node.title AS node_title,
taxonomy_term_data_field_data_field_graduate_department.description AS taxonomy_term_data_field_data_field_graduate_department_desc,
taxonomy_term_data_field_data_field_graduate_department.format AS taxonomy_term_data_field_data_field_graduate_department_form,
field_data_field_course_listing_title.field_course_listing_title_value AS field_data_field_course_listing_title_field_course_listing_t,
taxonomy_term_data_node.weight AS taxonomy_term_data_node_weight,
'node' AS field_data_field_course_listing_title_node_entity_type,
'node' AS field_data_field_course_secondary_text_node_entity_type,
'node' AS field_data_field_graduate_department_node_entity_type,
'node' AS field_data_field_graduate_course_mode_node_entity_type,
'node' AS field_data_field_graduate_course_duration_node_entity_type,
'node' AS field_data_field_course_listing_keywords_node_entity_type
FROM
{node} node
  LEFT JOIN (SELECT td.*, tn.nid AS nid FROM {taxonomy_term_data} td LEFT JOIN {taxonomy_vocabulary} tv ON td.vid = tv.vid LEFT JOIN {taxonomy_index} tn ON tn.tid = td.tid
  WHERE  (tv.machine_name IN  ('graduate_course_level')) ) taxonomy_term_data_node ON node.nid = taxonomy_term_data_node.nid

LEFT JOIN {field_data_field_graduate_department} field_data_field_graduate_department ON node.nid = field_data_field_graduate_department.entity_id AND (field_data_field_graduate_department.entity_type = 'node' AND field_data_field_graduate_department.deleted = '0')
LEFT JOIN {taxonomy_term_data} taxonomy_term_data_field_data_field_graduate_department ON field_data_field_graduate_department.field_graduate_department_tid = taxonomy_term_data_field_data_field_graduate_department.tid

LEFT JOIN {field_data_field_course_listing_title} field_data_field_course_listing_title ON node.nid = field_data_field_course_listing_title.entity_id AND (field_data_field_course_listing_title.entity_type = 'node' AND field_data_field_course_listing_title.deleted = '0')
WHERE (( (node.status = '1') AND (node.type IN  ('graduate_course')) ))
ORDER BY field_data_field_course_listing_title_field_course_listing_t ASC, taxonomy_term_data_node_weight ASC

---------------------------------------

SELECT GROUP_CONCAT(DISTINCT taxonomy_term_data_field_data_field_graduate_department.description ORDER BY taxonomy_term_data_field_data_field_graduate_department.description DESC SEPARATOR ', ')
FROM {field_data_field_graduate_department} field_data_field_graduate_department
WHERE field_data_field_graduate_department.entity_id = node.nid
AND field_data_field_graduate_department.entity_type = 'node'
AND field_data_field_graduate_department.deleted = '0'

LEFT JOIN {taxonomy_term_data} taxonomy_term_data_field_data_field_graduate_department
ON field_data_field_graduate_department.field_graduate_department_tid = taxonomy_term_data_field_data_field_graduate_department.tid

GROUP BY taxonomy_term_data_field_data_field_graduate_department.description


-----------------------

SELECT GROUP_CONCAT(DISTINCT groupedTax.entity_id ORDER BY groupedTax.description DESC SEPARATOR ', ') FROM
(
  SELECT * FROM (
    SELECT field_graduate_department_tid, entity_id
    FROM {field_data_field_graduate_department} field_data_field_graduate_department
    WHERE field_data_field_graduate_department.entity_id = node.nid
    AND field_data_field_graduate_department.entity_type = 'node'
    AND field_data_field_graduate_department.deleted = '0'
  ) as department

  LEFT JOIN {taxonomy_term_data} taxonomy_term_data_field_data_field_graduate_department
  ON department.field_graduate_department_tid = taxonomy_term_data_field_data_field_graduate_department.tid
  AND taxonomy_term_data_field_data_field_graduate_department

) as groupedTax
GROUP BY groupedTax.entity_id




------------------------------

SELECT FirstName, LastName,
       OrderCount = (SELECT COUNT(O.Id) FROM [Order] O WHERE O.CustomerId = C.Id)
  FROM Customer C


  https://dev.mysql.com/doc/refman/5.7/en/group-by-functions.html



--------------------------------

SELECT
DISTINCT node.nid AS nid,
node.title AS node_title,
(
  SELECT GROUP_CONCAT(DISTINCT groupedTax.entity_id ORDER BY groupedTax.description DESC SEPARATOR ', ') FROM
    (
      SELECT * FROM (
        SELECT field_graduate_department_tid, entity_id
        FROM {field_data_field_graduate_department} field_data_field_graduate_department
        WHERE field_data_field_graduate_department.entity_id = node.nid
        AND field_data_field_graduate_department.entity_type = 'node'
        AND field_data_field_graduate_department.deleted = '0'
      ) as department

      LEFT JOIN {taxonomy_term_data} taxonomy_term_data_field_data_field_graduate_department
      ON department.field_graduate_department_tid = taxonomy_term_data_field_data_field_graduate_department.tid
    ) as groupedTax
  GROUP BY groupedTax.entity_id
) as taxonomy_term_data_field_data_field_graduate_department_desc,
taxonomy_term_data_field_data_field_graduate_department.format AS taxonomy_term_data_field_data_field_graduate_department_form,
field_data_field_course_listing_title.field_course_listing_title_value AS field_data_field_course_listing_title_field_course_listing_t,
taxonomy_term_data_node.weight AS taxonomy_term_data_node_weight,
'node' AS field_data_field_course_listing_title_node_entity_type,
'node' AS field_data_field_course_secondary_text_node_entity_type,
'node' AS field_data_field_graduate_department_node_entity_type,
'node' AS field_data_field_graduate_course_mode_node_entity_type,
'node' AS field_data_field_graduate_course_duration_node_entity_type,
'node' AS field_data_field_course_listing_keywords_node_entity_type
FROM
{node} node
  LEFT JOIN (SELECT td.*, tn.nid AS nid FROM {taxonomy_term_data} td LEFT JOIN {taxonomy_vocabulary} tv ON td.vid = tv.vid LEFT JOIN {taxonomy_index} tn ON tn.tid = td.tid
  WHERE  (tv.machine_name IN  ('graduate_course_level')) ) taxonomy_term_data_node ON node.nid = taxonomy_term_data_node.nid

LEFT JOIN {field_data_field_course_listing_title} field_data_field_course_listing_title ON node.nid = field_data_field_course_listing_title.entity_id AND (field_data_field_course_listing_title.entity_type = 'node' AND field_data_field_course_listing_title.deleted = '0')
WHERE (( (node.status = '1') AND (node.type IN  ('graduate_course')) ))
ORDER BY field_data_field_course_listing_title_field_course_listing_t ASC, taxonomy_term_data_node_weight ASC