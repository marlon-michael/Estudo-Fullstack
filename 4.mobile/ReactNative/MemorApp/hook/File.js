import * as fs from 'expo-file-system'



function save(filename, content) {
  fs.StorageAccessFramework.requestDirectoryPermissionsAsync('file://storage/emulated/0/')
    .then(async permission => {
      if (!permission.granted) {
        return alert('permissão foi negada')
      }
      await fs.StorageAccessFramework.readDirectoryAsync(permission.directoryUri)
        .then(files => files.map(file => file.search(filename) + 1 && fs.deleteAsync(file).then(v => v)))
      const file = await fs.StorageAccessFramework.createFileAsync(permission.directoryUri, filename, 'application/text')
      fs.writeAsStringAsync(file, content)
    })
}

async function load(filename) {
  return await fs.StorageAccessFramework.requestDirectoryPermissionsAsync('file://storage/emulated/0/')
    .then(async permission => {
      if (!permission.granted) {
        return alert('no permission garanted')
      }
      return await fs.StorageAccessFramework.readDirectoryAsync(permission.directoryUri, { encoding: fs.EncodingType.UTF8 })
        .then(files => files.map(
          file => file.search(filename) >= 0 && fs.readAsStringAsync(file)
            .then(value => value)
        ).filter(x => x != false)[0]
        )
    })
}

export default { save, load }