//Using the previous code from main_test1, this test will 
@Grab('info.picocli:picocli-groovy:4.7.7')


import groovy.io.FileType
import groovy.picocli.CliBuilder

class dirScanner {
    def files(str = dirPath) {
        return null
    }

    def extractID(String line) {
    line.replaceAll(/[^0-9]/, "")
    int end = line.indexOf(')')
    return line.substring(end + 2)
    }
}
def findFile = new dirScanner()
def dirPath = System.getProperty("user.dir")
def dir = new File(dirPath)
OutputStream file1 = new FileOutputStream("file1.txt");

files = []
target = dirPath + "\\findme.txt"
def targetFile = new File(target)
dir.eachFileRecurse (FileType.ANY) { file -> if (file == targetFile){files.add(file)}}
println files
def findMe = new File(files[0].toString()).eachLine { line -> file1 << findFile.extractID(line) + "\n" }
file1.close()

def cli = new CliBuilder(usage: 'groovy main_test2.groovy [options]')
cli.with {
    h(longOpt: 'help', 'Show usage information')
    d(longOpt: 'directory', args: 1, argName: 'dir', 'Directory to scan')
    o(longOpt: 'output', args: 1, argName: 'file', 'Output file name')
}